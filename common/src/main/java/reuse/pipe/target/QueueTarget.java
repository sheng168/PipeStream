package reuse.pipe.target;

import java.util.Queue;

import reuse.pipe.base.AbstractTarget;


public class QueueTarget<T> extends AbstractTarget<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QueueTarget.class);
	
	private Queue<T> queue;

	public QueueTarget(Queue<T> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void send(T value) throws InterruptedException {
		queue.add(value);
	}

}
