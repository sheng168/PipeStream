package reuse.pipe.target;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamLineTarget extends OutputStreamAbstractTarget<String> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamLineTarget.class);
	
	private OutputStreamWriter dos;

	public OutputStreamLineTarget(final OutputStream outs)
			throws IOException {
		super(outs);
		dos = new OutputStreamWriter(outs, "UTF-8");	
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
	
}
