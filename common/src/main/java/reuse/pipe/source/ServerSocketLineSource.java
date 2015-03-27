package reuse.pipe.source;

import java.io.IOException;
import java.io.InputStream;

import reuse.pipe.api.NullTarget;
import reuse.pipe.api.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;

public class ServerSocketLineSource extends ServerSocketAbstractSource<String> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerSocketLineSource.class);
	
	public ServerSocketLineSource(int port, Target<String> target) throws IOException {
		super(port, target);
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		final ServerSocketLineSource source = 
			new ServerSocketLineSource(2011, 
				new LogDecorator<String>(
					new CountDecorator<String>(
						new NullTarget<String>())));		
	}

	@Override
	protected void createInputStreamSource(InputStream inputStream,
			Target<String> target) throws IOException {
		new InputStreamLineSource(inputStream, target);
	}

}
