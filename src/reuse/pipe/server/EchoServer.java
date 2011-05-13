package reuse.pipe.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.InputStreamByteBufferSource;
import reuse.pipe.target.OutputStreamByteBufferTarget;

public class EchoServer {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
		.getLogger(EchoServer.class);
	
	public EchoServer(int port) throws IOException {
		new ServerSocketSocketSource(port,
			new EchoSocketTarget()).run();
	}
	
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new EchoServer(port);
	}
}

class EchoSocketTarget extends AbstractSocketTarget<ByteBuffer>{

	@Override
	protected Source<ByteBuffer> createInputStreamSource(InputStream inputStream,
			Target<ByteBuffer> target) {
		return new InputStreamByteBufferSource(inputStream, target).start();
	}

	@Override
	protected Target<ByteBuffer> createOutStreamTarget(OutputStream os) {
		return //new AsyncDecorator<ByteBuffer>(
			new CountDecorator<ByteBuffer>(
				new ByteBufferCountDecorator(
					new OutputStreamByteBufferTarget(os)));//);
	}
	
}
