package reuse.pipe.pump;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.router.BroadcastBinaryRouter;
import reuse.pipe.source.BooleanSource;
import reuse.pipe.target.BlockingQueueTarget;

public class BlockingQueuePump<T> extends AbstractPump<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BlockingQueuePump.class);
	
	private BlockingQueue<T> queue;

	public BlockingQueuePump(BlockingQueue<T> queue, Target<T> target) {
		super(target);
		this.queue = queue;
	}

//	@Override
//	protected void poll() throws Exception {
//		feed(queue.take());
//	}

	@Override
	protected T convert(Boolean value) {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		// 11M / sec on T510
		final BlockingQueue<Boolean> bq = new LinkedBlockingQueue<Boolean>();
		new BooleanSource(
			new BroadcastBinaryRouter<Boolean>(
				new BlockingQueueTarget<Boolean>(bq)
				,
				new BlockingQueuePump<Boolean>(bq, 
					new CountDecorator<Boolean>(
						new NullTarget<Boolean>()
					)
				)
			)
		).run();
	}
}
