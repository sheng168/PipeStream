package reuse.pipe.source;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;

public class InputStreamByteSource extends InputStreamAbstractSource<Byte> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamByteSource.class);

	public InputStreamByteSource(InputStream inputStream, Target<Byte> target)
			throws IOException {
		super(inputStream, target);
	}

	protected void extract(InputStream is) throws IOException {
		int read;
		while ((read = is.read()) >= 0) {
			target.send(Byte.valueOf((byte) read));
		}
	}
	
	public static void main(String[] args) throws IOException {
		log.debug("max {}", Byte.MAX_VALUE);
		log.debug("max {}", Byte.MIN_VALUE);
		
		for (int i = 0; i <= 255; i++) {
			log.debug("int {} -> byte {}", i, (byte)i);
		}
		
		new InputStreamByteSource(new ByteArrayInputStream(new byte[1024*1024*50]), 
			new NullTarget<Byte>());
	}
}
