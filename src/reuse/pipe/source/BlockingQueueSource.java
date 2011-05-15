package reuse.pipe.source;

import java.util.concurrent.BlockingQueue;

import reuse.pipe.Target;

public class BlockingQueueSource<T> extends AbstractThreadedSource<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BlockingQueueSource.class);
	
	private BlockingQueue<T> queue;

	public BlockingQueueSource(BlockingQueue<T> queue, Target<T> target) {
		super(target);
		this.queue = queue;
	}

	@Override
	protected void poll() throws Exception {
		feed(queue.take());
	}
}
