package reuse.pipe.source;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;

public abstract class InputStreamSource<T> extends AbstractSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamSource.class);

	public InputStreamSource(final InputStream inputStream, Target<T> target)
			throws IOException {
		super(target);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					log.info("connection from {}", 1);

					extract(new BufferedInputStream(inputStream));
					log.info("stream closed {}", 1);

				} catch (IOException e) {
					log.warn("", e);
				}
			}
		}).start();
	}

	protected abstract void extract(final InputStream inputStream) throws IOException;
}
