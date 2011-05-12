package reuse.pipe.decorator;

import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.*;
import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class CountDecorator<T> extends Decorator<T> {
	
	private AtomicLong count = new AtomicLong();

	public CountDecorator(Target<T> target) {
		this("",target);
	}

	public CountDecorator(String name, Target<T> target) {
		super(target);

		new NumberAndDeltaMonitor(count, this+name+":name=count");
	}

	@Override
	public void send(T val) {
		count.incrementAndGet();
		super.send(val);
	}

}
