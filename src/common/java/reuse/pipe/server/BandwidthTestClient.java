package reuse.pipe.server;

import java.io.IOException;
import java.net.Socket;


public class BandwidthTestClient {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
		.getLogger(BandwidthTestClient.class);

	public BandwidthTestClient(String host, int port) throws IOException {
		log.debug("connecting to {} {}", host, port);
		new PumpSocketTarget().send(new Socket(host, port));
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String host = System.getProperty("host", "localhost");
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new BandwidthTestClient(host, port);
		
//		TimeUnit.DAYS.sleep(1);
	}
}
