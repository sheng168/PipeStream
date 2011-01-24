package reuse.pipe.target;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import reuse.pipe.Decorator;
import reuse.pipe.Target;
import reuse.pipe.decorator.AsyncDecorator;
import reuse.pipe.source.CounterSource;

public class SocketDecorator extends Target<Object> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SocketDecorator.class);
	private BufferedWriter bufferedWriter;

	public SocketDecorator(String host, int port, Target<Object> target) throws UnknownHostException, IOException {
//		super(target);
		
		bufferedWriter = new BufferedWriter(
			new OutputStreamWriter(
				new Socket(host, port).getOutputStream(), Charset.forName("UTF-8")));
	}

	@Override
	public void send(Object o) {
		try {
			bufferedWriter.write(o.toString());
			bufferedWriter.write('\n');
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		super.send(o);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new CounterSource(5000*1000, 
			new AsyncDecorator<Object>(
				new SocketDecorator("localhost", 2011, new Target<Object>())));
	}
}
