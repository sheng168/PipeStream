package reuse.pipe.decorator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import reuse.jmx.NumberAndDeltaMonitor;
import reuse.jmx.numbers.ObjectNumber;
import reuse.pipe.Decorator;
import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.source.BlockingQueueSource;
import reuse.pipe.source.BooleanSource;
import reuse.pipe.thread.ThreadSafe;
import reuse.util.Function;

public class AsyncDecorator<T> extends Decorator<T> implements ThreadSafe {
	final BlockingQueue<T> queue;
	
	private BlockingQueueSource<T> source;

	public AsyncDecorator(Target<T> target) {
		this(new LinkedBlockingQueue<T>(1000), target);
	}
	
	public AsyncDecorator(BlockingQueue<T> queue, Target<T> target) {
		super(target);
		this.queue = queue;
		new NumberAndDeltaMonitor(new ObjectNumber<BlockingQueue<T>>(queue, new Function<BlockingQueue<T>, Number>() {
			@Override
			public Number apply(BlockingQueue<T> q) {
				return q.size();
			}
		}), this+":name=queueSize");
		source = new BlockingQueueSource<T>(queue, target);
		source.start();
	}

	@Override
	public void send(T o) throws InterruptedException {
		queue.put(o);
	}
	
	@Override
	public void setTarget(Target<T> target) {
		source.setTarget(target);
		super.setTarget(target);
	}

	public static void main(String[] args) {
		final BlockingQueue<Boolean> bq;
		// on T510
		// 5 - 7M / sec
		bq = new LinkedBlockingQueue<Boolean>(10000);
		// 5 - 7M / sec
//		bq = new LinkedBlockingQueue<Boolean>(1000);
		// 4-5M / sec
//		bq = new ArrayBlockingQueue<Boolean>(1000);
		// 3M
//		bq = new SynchronousQueue<Boolean>();
		
		new BooleanSource(
			new AsyncDecorator<Boolean>(bq, 
				new CountDecorator<Boolean>(
					new NullTarget<Boolean>()
				)
			)
		).run();
	}
}
