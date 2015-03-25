package reuse.pipe.server;

import java.io.IOException;

public class BandwidthTestServer {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
		.getLogger(BandwidthTestServer.class);
	
	public BandwidthTestServer(int port) throws IOException {
		new ServerSocketSocketSource(port,
			new PumpSocketTarget()).run();
	}
	
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(System.getProperty("port", "2011"));
		
		new BandwidthTestServer(port);
	}
}

