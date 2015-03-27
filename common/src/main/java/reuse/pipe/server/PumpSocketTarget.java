package reuse.pipe.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import reuse.pipe.api.NullTarget;
import reuse.pipe.api.Source;
import reuse.pipe.api.Target;
import reuse.pipe.decorator.ByteBufferCountDecorator;
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
				try {
					while (true)
						out.send(bb);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start(); // pump data out independent of input
		
		return out;
	}
	
}