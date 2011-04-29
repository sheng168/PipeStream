package reuse.pipe.demo;

import java.io.IOException;
import java.net.Socket;

import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;
import reuse.pipe.router.TapRouter;
import reuse.pipe.source.*;
import reuse.pipe.target.OutputStreamLineTarget;

public class LineEchoClient {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 2012);
		
		OutputStreamLineTarget target = new OutputStreamLineTarget(socket.getOutputStream());
		new InputStreamLineSource(socket.getInputStream(),
				target);
		
		for (int i = 0; i < 1; i++) {
			target.send("ping"+i);
		}
	}
}
