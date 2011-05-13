package reuse.pipe.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;
import reuse.pipe.router.TapRouter;
import reuse.pipe.source.InputStreamByteBufferSource;
import reuse.pipe.source.ServerSocketAbstractSource;
import reuse.pipe.target.OutputStreamByteBufferTarget;

public class EchoServerOld {
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new ServerSocketAbstractSource<ByteBuffer>(port, 
			new CountDecorator<ByteBuffer>(
				new LogDecorator<ByteBuffer>(
					new NullTarget<ByteBuffer>()
				)
			)
		) {
			@Override
			protected void handleSocket(Socket socket, Target<ByteBuffer> target) throws IOException {
				super.handleSocket(socket, 
					new TapRouter<ByteBuffer>(new OutputStreamByteBufferTarget(socket.getOutputStream()), target));
			}

			@Override
			protected void createInputStreamSource(InputStream inputStream,
					Target<ByteBuffer> target) throws IOException {
				new InputStreamByteBufferSource(inputStream, target);
			}

		};
	}
}
