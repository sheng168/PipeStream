package reuse.pipe.decorator;

import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.*;
import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

public class CountDecorator<T> extends Decorator<T> {
	
	private AtomicLong count = new AtomicLong();

	public Number getCount() {
		return count;
	}

	public CountDecorator(Target<T> target) {
		this("",target);
	}

	public CountDecorator(String name, Target<T> target) {
		super(target);

		new NumberAndDeltaMonitor(count, this+name+":name=count");
	}

	@Override
	public void send(T val) throws Exception {
		count.incrementAndGet();
		super.send(val);
	}

	
}
