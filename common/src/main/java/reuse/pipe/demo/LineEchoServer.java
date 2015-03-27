package reuse.pipe.demo;

import java.io.IOException;
import java.net.Socket;

import reuse.pipe.api.NullTarget;
import reuse.pipe.api.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.router.BroadcastBinaryRouter;
import reuse.pipe.source.ServerSocketLineSource;
import reuse.pipe.target.OutputStreamLineTarget;

public class LineEchoServer {
	public static void main(String[] args) throws IOException {
		new ServerSocketLineSource(2012, 
			new CountDecorator<String>(
//				new LogDecorator<String>(
						new NullTarget<String>()
//				)
						)) {
			@Override
			protected void handleSocket(Socket socket, Target<String> target) throws IOException {
				super.handleSocket(socket, 
					new BroadcastBinaryRouter<String>(new OutputStreamLineTarget(socket.getOutputStream()), target));
			}
		};
	}
}
