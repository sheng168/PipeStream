package reuse.pipe.pump;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.BooleanSource;

public class NanoTimePump extends AbstractPump<Long> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NanoTimePump.class);
	
	public NanoTimePump(Target<Long> target) {
		super(target);
	}

	@Override
	protected Long convert(Boolean value) {
		return System.nanoTime();
	}

	public static void main(String[] args) {
		// 27M / sec on T510
		new BooleanSource(
//			new CountDecorator<Boolean>(
//				new NullTarget<Boolean>()
				new NanoTimePump(
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
