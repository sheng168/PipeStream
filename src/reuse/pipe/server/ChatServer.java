package reuse.pipe.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import reuse.pipe.Decorator;
import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.decorator.ByteBufferCountDecorator;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.router.BroadcastRouter;
import reuse.pipe.source.InputStreamByteBufferSource;
import reuse.pipe.target.OutputStreamByteBufferTarget;

public class ChatServer {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
		.getLogger(ChatServer.class);
	
	public ChatServer(int port) throws IOException {
		new ServerSocketSocketSource(port,
			new ChatSocketTarget()).run();
	}
	
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new ChatServer(port);
	}
}

class ChatSocketTarget extends AbstractSocketTarget<ByteBuffer>{
	private final ArrayList<Target<ByteBuffer>> TARGETS = new ArrayList<Target<ByteBuffer>>();
	BroadcastRouter<ByteBuffer> broadcast = new BroadcastRouter<ByteBuffer>(TARGETS);
	
	@Override
	protected Source<ByteBuffer> createInputStreamSource(InputStream inputStream,
			Target<ByteBuffer> target) {
		TARGETS.add(target);
		return new InputStreamByteBufferSource(inputStream, 
			new BlockClose<ByteBuffer>(
				broadcast
			)
		).start();
	}

	@Override
	protected Target<ByteBuffer> createOutStreamTarget(OutputStream os) {
		return new ExceptionHandler(
			new CountDecorator<ByteBuffer>(
				new ByteBufferCountDecorator(
					new OutputStreamByteBufferTarget(os))));
	}
	
	class ExceptionHandler extends Decorator<ByteBuffer> {	
		@Override
		public void send(ByteBuffer val) throws Exception {
			try {
				super.send(val);
			} catch (Exception e) {
				broadcast.remove(this);
			}
		}
	
		public ExceptionHandler(Target<ByteBuffer> target) {
			super(target);
		}		
	}
}

class BlockClose<T> extends Decorator<T> {	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
//		super.close();
	}

	public BlockClose(Target<T> target) {
		super(target);
	}		
}

