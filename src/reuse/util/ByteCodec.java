package reuse.util;

public class ByteCodec {
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static void main(String[] args) {
		String hex = "00 00 00 2F 00 00 00 02 00 00 00 00 00 00 00 01 53 4D 50 50 33 54 45 53 54 00\r\n" + 
				"73 65 63 72 65 74 30 38 00 53 55 42 4D 49 54 31 00 00 01 01 00";
		
		byte[] buf = ByteCodec.hexEtcStringToByteArray(hex);
		System.out.println(buf.length);
	}

	/**
	 * remove white space chars before to byte[]
	 * @param s
	 * @return
	 */
	public static byte[] hexEtcStringToByteArray(String s) {
		return ByteCodec.hexStringToByteArray(s.replaceAll("\\s", ""));
	}
}
