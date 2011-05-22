package reuse.pipe.demo;

import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.LongValue;
import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.LogDecorator;
import reuse.pipe.decorator.SpacerDelayDecorator;
import reuse.pipe.pump.CounterPump;
import reuse.pipe.pump.SystemTimePump;
import reuse.pipe.source.AbstractSource;
import reuse.pipe.source.BooleanSource;

public class TestDriver extends AbstractSource<Long> {
	public TestDriver(Target<Long> target) {
		super(target);

		AtomicLong millis = new AtomicLong(1000);
		new LongValue(millis, this + ":name=outputDelayMillis");

		new BooleanSource(
			new SystemTimePump( 
				new CountDecorator<Long>("out",
					new SpacerDelayDecorator<Long>(millis , target)
					))).run();
	}

	public static void main(String[] args) {
		new TestDriver(new LogDecorator<Long>(new NullTarget<Long>()));
	}
}
