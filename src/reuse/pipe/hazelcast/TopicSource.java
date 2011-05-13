package reuse.pipe.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.MessageListener;

import reuse.pipe.Target;
import reuse.pipe.source.AbstractSource;


public class TopicSource<T> extends AbstractSource<T> implements MessageListener<T> {
	private ITopic<T> topic;

	public TopicSource(ITopic<T> topic, Target<T> target) {
		super(target);
		this.topic = topic;
		this.topic.addMessageListener(this);
	}
	
	public TopicSource(String topic, Target<T> target) {
		this(Hazelcast.<T>getTopic(topic), target);
	}
	
	@Override
	public void onMessage(T value) {
		target.send(value);
	}
	
}
