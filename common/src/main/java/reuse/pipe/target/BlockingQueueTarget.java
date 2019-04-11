package reuse.pipe.target;

import java.util.concurrent.BlockingQueue;

import reuse.pipe.base.AbstractTarget;


public class BlockingQueueTarget<T> extends AbstractTarget<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BlockingQueueTarget.class);
	
	private BlockingQueue<T> queue;

	public BlockingQueueTarget(BlockingQueue<T> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void send(T value) throws InterruptedException {
		queue.put(value);
	}

}
