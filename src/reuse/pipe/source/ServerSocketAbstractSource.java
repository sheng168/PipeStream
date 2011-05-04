package reuse.pipe.source;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.MDC;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;

public abstract class ServerSocketAbstractSource<T> extends AbstractSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerSocketAbstractSource.class);
	
	public ServerSocketAbstractSource(int port, Target<T> target) throws IOException {
		super(target);
		
		log.info("listing on port {}", port);
		final ServerSocket ss = new ServerSocket(port);
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					try {
						final Socket socket = ss.accept();
						
						log.debug("socket connected {}", socket);
						MDC.put("host", ""+socket.getRemoteSocketAddress());
						MDC.put("port", ""+socket.getLocalPort());
						handleSocket(socket, ServerSocketAbstractSource.this.target);
					} catch (IOException e) {
						log.error(e.toString(), e);
					}
				}
				
			}
		}, ServerSocketAbstractSource.class.getSimpleName() + ' ' + port).start();
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
