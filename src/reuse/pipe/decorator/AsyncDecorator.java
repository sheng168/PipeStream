package reuse.pipe.decorator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import reuse.pipe.Decorator;
import reuse.pipe.Target;
import reuse.pipe.ThreadSafe;
import reuse.pipe.source.BlockingQueueSource;

public class AsyncDecorator<T> extends Decorator<T> implements ThreadSafe {
	BlockingQueue<T> queue = new LinkedBlockingQueue<T>(10*1000);
	
	private BlockingQueueSource<T> source;

	public AsyncDecorator(Target<T> target) {
		super(target);

		source = (BlockingQueueSource<T>) new BlockingQueueSource<T>(queue, target).start();
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

}
