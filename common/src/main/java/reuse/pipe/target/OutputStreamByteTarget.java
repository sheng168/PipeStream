package reuse.pipe.target;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamByteTarget extends OutputStreamAbstractTarget<Byte> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamByteTarget.class);

	public OutputStreamByteTarget(final OutputStream os) {
		super(os);
	}

	@Override
	public void send(Byte line) {	
		try {
			super.os.write(line);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
