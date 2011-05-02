package reuse.pipe.target;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import reuse.pipe.Target;

public class OutputStreamLineTarget extends Target<String> implements Closeable{
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamLineTarget.class);
	
	private OutputStreamWriter dos;

	public OutputStreamLineTarget(final OutputStream outs)
			throws IOException {
		dos = new OutputStreamWriter(new BufferedOutputStream(outs), "UTF-8");	
	}

	@Override
	public void send(String line) {	
		try {
			dos.write(line);
			dos.write('\n');
			dos.flush();
		} catch (IOException e) {
			log.error(e.toString(), e);
		}
	}
	
	@Override
	public void close() throws IOException {
		dos.close();
	}

}
