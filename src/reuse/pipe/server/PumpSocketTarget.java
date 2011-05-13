package reuse.pipe.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import reuse.pipe.NullTarget;
import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.source.InputStreamByteBufferSource;
import reuse.pipe.target.OutputStreamByteBufferTarget;

class PumpSocketTarget extends AbstractSocketTarget<ByteBuffer> {
	@Override
	protected Source<ByteBuffer> createInputStreamSource(InputStream inputStream,
			Target<ByteBuffer> target) {
		return new InputStreamByteBufferSource(inputStream, 
			new ByteBufferCountDecorator("in",
					new NullTarget<ByteBuffer>() // throw away input
			)
		).start();
	}

	@Override
	protected Target<ByteBuffer> createOutStreamTarget(OutputStream os) {
		final Target<ByteBuffer> out = new ByteBufferCountDecorator("out", 
			new OutputStreamByteBufferTarget(os)
		);
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				ByteBuffer bb = ByteBuffer.wrap(new byte[8*1024]);
				while (true)
					out.send(bb);
			}
		}).start(); // pump data out independent of input
		
		return out;
	}
	
}