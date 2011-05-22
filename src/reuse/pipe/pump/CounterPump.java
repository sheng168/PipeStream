package reuse.pipe.pump;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.BooleanSource;

public class CounterPump extends AbstractPump<Long> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CounterPump.class);

	long n;
	
	public CounterPump(Target<Long> target) {
		super(target);
	}

	@Override
	protected Long convert(Boolean value) {
		return n++;
	}

	public static void main(String[] args) {
		// 60M / sec on T510
		new BooleanSource(
//			new CountDecorator<Boolean>(
//				new NullTarget<Boolean>()
				new CounterPump(
					new CountDecorator<Long>(
//						new LogDecorator<Long>(
							new NullTarget<Long>()
//						)
					)
				)
//			)
		).run();
	}
}
