package reuse.pipe.pump;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.BooleanSource;

import com.google.common.annotations.Beta;

@Beta
public class IntCounterPump extends AbstractPump<Integer> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IntCounterPump.class);

	int n;
	
	public IntCounterPump(Target<Integer> target) {
		super(target);
	}

	@Override
	protected Integer convert(Boolean value) {
		return n++;
	}

	public static void main(String[] args) {
		// 60M / sec on T510
		new BooleanSource(
//			new CountDecorator<Boolean>(
//				new NullTarget<Boolean>()
				new IntCounterPump(
					new CountDecorator<Integer>(
//						new LogDecorator<Long>(
							new NullTarget<>()
//						)
					)
				)
//			)
		).run();
	}
}
