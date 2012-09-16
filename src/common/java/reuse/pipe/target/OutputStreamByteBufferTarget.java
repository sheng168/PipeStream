package reuse.pipe.target;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import reuse.pipe.source.InputStreamByteBufferSource;

public class OutputStreamByteBufferTarget extends OutputStreamAbstractTarget<ByteBuffer> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(OutputStreamByteBufferTarget.class);

	public OutputStreamByteBufferTarget(final OutputStream os) {
		super(os);
	}

	@Override
	public void send(ByteBuffer bb) {	
		try {
			super.os.write(bb.array(), bb.arrayOffset(), bb.limit());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		new InputStreamByteBufferSource(new FileInputStream("C:\\Users\\shengyu\\Dropbox\\shared.real\\EHI1.zip"), 
			new OutputStreamByteBufferTarget(new FileOutputStream("C:\\Users\\shengyu\\Dropbox\\shared.real\\EHI2.zip")));
	}
}
