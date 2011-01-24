package reuse.pipe.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import reuse.pipe.Target;

public class InputStreamLineSource extends InputStreamSource<String> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamLineSource.class);

	public InputStreamLineSource(final InputStream inputStream, Target<String> target)
			throws IOException {
		super(inputStream, target);
	}

	protected void extract(final InputStream inputStream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));

		String line;
		while ((line = br.readLine()) != null) {
			// log.info("input: {}", line);
			target.send(line);
		}
	}
}
