package reuse.pipe.hazelcast;


public class TopicTargetTest {
	public static void main(String[] args) {
		byte[] buf = new byte[1*1024/1024];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte)i;
		}
		
		TopicTarget<byte[]> t = new TopicTarget<byte[]>("topic");
		while(true) {
			t.send(buf);
		}
		
	}
}
