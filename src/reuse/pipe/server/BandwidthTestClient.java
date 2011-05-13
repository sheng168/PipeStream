package reuse.pipe.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import reuse.pipe.NullTarget;
import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.source.InputStreamByteBufferSource;
import reuse.pipe.target.OutputStreamByteBufferTarget;

public class BandwidthTestClient {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
		.getLogger(BandwidthTestClient.class);

	public BandwidthTestClient(String host, int port) throws IOException {
		log.debug("connecting to {} {}", host, port);
		new PumpSocketTarget().send(new Socket(host, port));
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String host = System.getProperty("host", "localhost");
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new BandwidthTestClient(host, port);
		
//		TimeUnit.DAYS.sleep(1);
	}
}

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
