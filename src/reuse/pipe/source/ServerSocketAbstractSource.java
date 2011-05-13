package reuse.pipe.source;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.MDC;

import reuse.pipe.Target;
import reuse.pipe.source.AbstractThreadedSource;

public abstract class ServerSocketAbstractSource<T> extends AbstractThreadedSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerSocketAbstractSource.class);
	private ServerSocket ss;
	
	public ServerSocketAbstractSource(int port, Target<T> target) throws IOException {
		super(target);
		
		log.info("listing on port {}", port);
		ss = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				final Socket socket = ss.accept();
				
				log.debug("socket connected {}", socket);
				MDC.put("host", ""+socket.getRemoteSocketAddress());
				MDC.put("port", ""+socket.getPort());
				this.handleSocket(socket, ServerSocketAbstractSource.this.target);
			} catch (IOException e) {
				log.error(e.toString(), e);
			}
		}
		
	}

	/**
	 * handle newly accepted socket
	 */
	protected void handleSocket(final Socket socket, Target<T> target) throws IOException {
		createInputStreamSource(socket.getInputStream(), target);
	}
	
	protected abstract void createInputStreamSource(InputStream inputStream,
			Target<T> target) throws IOException;
}