package reuse.pipe.hazelcast;

import reuse.pipe.NullTarget;
import reuse.pipe.decorator.CountDecorator;

public class TopicTest {
	public static void main(String[] args) {
		byte[] buf = new byte[8*1024];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte)i;
		}
		
		new TopicSource<byte[]>("topic", 
				new CountDecorator<byte[]>(new NullTarget<byte[]>()));
		
		TopicTarget<byte[]> t = new TopicTarget<byte[]>("topic");
		while(true) {
			t.send(buf);
		}
		
	}
}
