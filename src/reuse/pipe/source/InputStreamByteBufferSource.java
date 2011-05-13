package reuse.pipe.source;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;

public class InputStreamByteBufferSource extends InputStreamAbstractSource<ByteBuffer> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamByteBufferSource.class);

	public InputStreamByteBufferSource(InputStream inputStream, Target<ByteBuffer> target) {
		super(inputStream, target);
	}

	protected void extract(InputStream is) throws IOException {
		byte[] buf = new byte[8*1024];
		int read;
		while ((read = is.read(buf)) >= 0) {
			feed(ByteBuffer.wrap(buf, 0, read));
//			buf = new byte[8*1024]; //TODO make a decision here
		}
	}

	public static void main(String[] args) throws IOException {
		new InputStreamByteBufferSource(new ByteArrayInputStream(new byte[1024*1024*50]), 
			new LogDecorator<ByteBuffer>(
				new NullTarget<ByteBuffer>())).start();
	}
}
