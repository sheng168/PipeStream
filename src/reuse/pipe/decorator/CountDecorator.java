package reuse.pipe.decorator;

import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.*;
import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class CountDecorator<T> extends Decorator<T> {
	
	private AtomicLong count;

	public CountDecorator(Target<T> target) {
		super(target);
		
		count = new AtomicLong();
		new NumberAndDeltaMonitor(count, "real.test:type=count");
	}

	@Override
	public void send(T o) {
		count.incrementAndGet();
		super.send(o);
	}

}
