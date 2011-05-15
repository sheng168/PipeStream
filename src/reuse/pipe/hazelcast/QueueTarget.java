package reuse.pipe.hazelcast;

import reuse.pipe.target.BlockingQueueTarget;

import com.hazelcast.core.Hazelcast;

public class QueueTarget<T> extends BlockingQueueTarget<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QueueTarget.class);
	
	public QueueTarget(String name) {
		super(Hazelcast.<T>getQueue(name));
	}
}
