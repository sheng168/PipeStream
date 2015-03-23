package reuse.pipe.demo;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.AsyncDecorator;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.decorator.DelayDecorator;
import reuse.pipe.pump.AbstractPump;
import reuse.pipe.source.BooleanSource;

public class PipeLining extends AbstractPump<Long> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PipeLining.class);
	
	public PipeLining(Target<Long> target) {
		super(target);
	}

	@Override
	protected Long convert(Boolean value) {
		return System.nanoTime();
	}

	public static void main(String[] args) {
		// 27M / sec on T510
		new BooleanSource(
			new DelayDecorator<Boolean>(1, 0,
				new AsyncDecorator<Boolean>( // ~1000 with this, ~500 without
					new DelayDecorator<Boolean>(1, 0,
						new PipeLining(
							new CountDecorator<Long>(
								new NullTarget<Long>()
							)
						)
					)
				)
			)
		).run();
	}
}
