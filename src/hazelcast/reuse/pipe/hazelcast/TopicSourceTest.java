package reuse.pipe.hazelcast;

import reuse.pipe.NullTarget;
import reuse.pipe.decorator.CountDecorator;

public class TopicSourceTest {
	public static void main(String[] args) {
		new TopicSource<byte[]>("topic", 
			new CountDecorator<byte[]>(new NullTarget<byte[]>()));		
	}
}
