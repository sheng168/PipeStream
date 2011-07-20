package reuse.jmx.demo;

import reuse.pipe.NullTarget;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.DelayDecorator;
import reuse.pipe.source.CounterSource;

public class CountDecoratorDemo {
	public static void main(String[] args) {
		new CounterSource(1000,
			new DelayDecorator<Long>(1000, 0, 
				new CountDecorator<Long>(new NullTarget<Long>()))).run();
	}
}
