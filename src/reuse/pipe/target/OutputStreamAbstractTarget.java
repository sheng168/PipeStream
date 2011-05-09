package reuse.pipe.target;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import reuse.pipe.NullTarget;

public class OutputStreamAbstractTarget<T> extends NullTarget<T> implements Closeable{
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamAbstractTarget.class);
	
	private OutputStream os;

	public OutputStreamAbstractTarget(OutputStream os) {
		super();
		this.os = os;
	}

	@Override
	public void close() throws IOException {
		os.close();
	}
}
