package reuse.pipe.target;

import java.io.IOException;
import java.io.OutputStream;

import reuse.pipe.api.Target;

public abstract class OutputStreamAbstractTarget<T>  implements Target<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamAbstractTarget.class);
	
	protected OutputStream os;

	public OutputStreamAbstractTarget(OutputStream os) {
		super();
		this.os = os;
	}

	@Override
	public void close() throws IOException {
		os.close();
	}

	@Override
	public void flush() {
		try {
			os.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
