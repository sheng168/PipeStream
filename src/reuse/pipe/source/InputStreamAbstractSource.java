package reuse.pipe.source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import reuse.pipe.Target;

public abstract class InputStreamAbstractSource<T> extends AbstractThreadedSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamAbstractSource.class);
	
	InputStream is;
	
	public InputStreamAbstractSource(final InputStream is, Target<T> target) {
		super(target);
		this.is = is;
	}

	@Override
	public void run() {
		try {
			log.debug("reading input stream");
			extract(new BufferedInputStream( // should we impose buffering?
					is)
			);
			InputStreamAbstractSource.this.target.close();
			log.debug("stream closed");
		} catch (EOFException e) {
			log.info("connection closed {}", e.toString());
		} catch (IOException e) {
			log.warn("", e);
		}
	}

	protected abstract void extract(final InputStream inputStream) throws IOException;
}
