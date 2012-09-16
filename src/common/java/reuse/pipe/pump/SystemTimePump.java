package reuse.pipe.pump;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.BooleanSource;

public class SystemTimePump extends AbstractPump<Long> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SystemTimePump.class);
	
	public SystemTimePump(Target<Long> target) {
		super(target);
	}

	@Override
	protected Long convert(Boolean value) {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		// 33M / sec on T510
		new BooleanSource(
//			new CountDecorator<Boolean>(
//				new NullTarget<Boolean>()
				new SystemTimePump(
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
