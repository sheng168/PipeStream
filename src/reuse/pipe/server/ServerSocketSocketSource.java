

package reuse.pipe.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.MDC;

import reuse.pipe.Target;
import reuse.pipe.source.AbstractThreadedSource;

public class ServerSocketSocketSource extends AbstractThreadedSource<Socket> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerSocketSocketSource.class);
	private ServerSocket ss;
	
	public ServerSocketSocketSource(int port, Target<Socket> target) throws IOException {
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
				this.feed(socket);
			} catch (IOException e) {
				log.error(e.toString(), e);
			}
		}
		
	}

}
