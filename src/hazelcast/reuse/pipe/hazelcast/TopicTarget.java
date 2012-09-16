package reuse.pipe.hazelcast;

import reuse.pipe.AbstractTarget;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ITopic;

public class TopicTarget<T> extends AbstractTarget<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TopicTarget.class);
	
	private ITopic<T> topic;

	public TopicTarget(ITopic<T> topic) {
		super();
		this.topic = topic;
	}

	public TopicTarget(String topic) {
		this(Hazelcast.<T>getTopic(topic));
	}
	
	@Override
	public void send(T value) {
		topic.publish(value);
	}

}
