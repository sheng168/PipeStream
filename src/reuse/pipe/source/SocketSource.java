package reuse.pipe.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;

public class SocketSource extends AbstractSource {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SocketSource.class);
	
	public SocketSource(int port, Target target) throws IOException {
		super(target);
		
		log.info("listing on port {}", port);
		final ServerSocket ss = new ServerSocket(port);
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					try {
						final Socket socket = ss.accept();
						
						new Thread(new Runnable(){

							@Override
							public void run() {
								try {
									log.info("connection from {}", socket.getRemoteSocketAddress());
									BufferedReader br = new BufferedReader(
											new InputStreamReader(
													socket.getInputStream()));
									
									String line;
									while ((line = br.readLine()) != null) {
//										log.info("input: {}", line);
										SocketSource.this.target.send(line);
									}
									log.info("connection closed {}", socket.getRemoteSocketAddress());
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}).start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final SocketSource source = 
			new SocketSource(2011, 
					new LogDecorator(
						new CountDecorator(
							new Target())));
		
	}

}
