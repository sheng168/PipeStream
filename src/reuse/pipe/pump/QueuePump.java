package reuse.pipe.pump;

import java.util.LinkedList;
import java.util.Queue;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.router.BroadcastBinaryRouter;
import reuse.pipe.source.BooleanSource;
import reuse.pipe.target.QueueTarget;

public class QueuePump<T> extends AbstractPump<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QueuePump.class);
	
	private Queue<T> queue;

	public QueuePump(Queue<T> queue, Target<T> target) {
		super(target);
		this.queue = queue;
	}

//	@Override
//	protected void poll() throws Exception {
//		feed(queue.take());
//	}

	@Override
	protected T convert(Boolean value) {
//		try {
			return queue.poll();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
	}

	public static void main(String[] args) {
		// 27M / sec on T510
		final Queue<Boolean> bq = new LinkedList<Boolean>();
		new BooleanSource(
			new BroadcastBinaryRouter<Boolean>(
				new QueueTarget<Boolean>(bq)
				,
				new QueuePump<Boolean>(bq, 
					new CountDecorator<Boolean>(
						new NullTarget<Boolean>()
					)
				)
			)
		).run();
	}
}
