package reuse.pipe.source;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;

public abstract class InputStreamAbstractSource<T> extends AbstractSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamAbstractSource.class);

	public InputStreamAbstractSource(final InputStream inputStream, Target<T> target)
			throws IOException {
		super(target);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					log.debug("reading input stream");
					extract(new BufferedInputStream(inputStream));
					log.debug("stream closed");
				} catch (IOException e) {
					log.warn("", e);
				}
			}
		}).start();
	}

	protected abstract void extract(final InputStream inputStream) throws IOException;
}
