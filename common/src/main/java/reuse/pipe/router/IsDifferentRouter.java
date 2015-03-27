package reuse.pipe.router;

import reuse.pipe.api.NullTarget;
import reuse.pipe.api.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.pump.NanoTimePump;
import reuse.pipe.source.BooleanSource;

public class IsDifferentRouter<T> extends AbstractBinaryRouter<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(IsDifferentRouter.class);

	T previous;

	public IsDifferentRouter(Target<T> targetTrue, Target<T> targetFalse) {
		super(targetTrue, targetFalse);
	}

	@Override
	protected boolean route(T val) {
		try {
			return val.equals(previous);
		} finally {
			previous = val;
		}
	}

	public static void main(String[] args) {
		new BooleanSource(new NanoTimePump(new IsDifferentRouter<Long>(
				new CountDecorator<Long>("unique", new NullTarget<Long>()),
				new CountDecorator<Long>("dup", new NullTarget<Long>())))).run();
	}
}
