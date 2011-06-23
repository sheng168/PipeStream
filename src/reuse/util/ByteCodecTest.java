package reuse.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ByteCodecTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHexStringToByteArray() {
		final byte[] bytes = ByteCodec.hexStringToByteArray("0791448720003023240DD0E474D81C0EBB010000111011315214000BE474D81C0EBB5DE3771B");
		final String s = new String(bytes);
		System.out.println(s);
	}

	@Test
	public void testHexEtcStringToByteArray() {
		fail("Not yet implemented");
	}

}
