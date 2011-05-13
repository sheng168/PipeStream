package reuse.pipe.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.target.AbstractTarget;

public abstract class AbstractSocketTarget<T> extends AbstractTarget<Socket>{

	@Override
	public void send(Socket s) {
		handleSocket(s);
	}
	
	/**
	 * handle newly accepted socket
	 */
	protected void handleSocket(final Socket socket) {
		Target<T> target;
		try {
			target = createOutStreamTarget(socket.getOutputStream());
			createInputStreamSource(socket.getInputStream(), target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract Target<T> createOutStreamTarget(OutputStream outputStream);

	protected abstract Source<T> createInputStreamSource(InputStream inputStream,
			Target<T> target) throws IOException;

}

