package reuse.pipe.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import reuse.pipe.Target;
import reuse.pipe.source.AbstractSource;


public class TopicSource<E> extends AbstractSource<E> implements MessageListener<E> {
	private ITopic<E> topic;

	public TopicSource(ITopic<E> topic, Target<E> target) {
		super(target);
		this.topic = topic;
		this.topic.addMessageListener(this);
	}
	
	public TopicSource(String topic, Target<E> target) {
		this(Hazelcast.<E>getTopic(topic), target);
	}
	
	@Override
	public void onMessage(Message<E> value) {
		try {
			feed(value.getMessageObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
