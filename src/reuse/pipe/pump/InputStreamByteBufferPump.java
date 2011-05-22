package reuse.pipe.pump;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.LogDecorator;
import reuse.pipe.source.BooleanSource;
import reuse.pipe.thread.ThreadMarker;

public class InputStreamByteBufferPump extends InputStreamAbstractPump<ByteBuffer> implements ThreadMarker.Blocking {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamByteBufferPump.class);

	public InputStreamByteBufferPump(InputStream inputStream, Target<ByteBuffer> target) {
		super(inputStream, target);
	}

	@Override
	protected ByteBuffer convert(Boolean value) throws IOException, Exception {
		byte[] buf = new byte[8*1024];
		int read;
		if ((read = is.read(buf)) >= 0) {
			return (ByteBuffer.wrap(buf, 0, read));
//			buf = new byte[8*1024]; //TODO make a decision here
		} else {
			throw new EOFException();
		}
	}


	public static void main(String[] args) throws IOException {
		new BooleanSource(
			new InputStreamByteBufferPump(new ByteArrayInputStream(new byte[1024*1024*50]), 
				new LogDecorator<ByteBuffer>(
					new NullTarget<ByteBuffer>()))).start();
	}
}
