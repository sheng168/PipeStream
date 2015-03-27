package reuse.pipe.router;

import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

public abstract class AbstractBinaryRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractBinaryRouter.class);

	Target<T> targetFalse;
	
	public AbstractBinaryRouter(Target<T> targetTrue, Target<T> targetFalse) {
		super(targetTrue);
		this.targetFalse = targetFalse;
	}

	@Override
	public void send(T val) throws Exception {
		if (route(val))
			super.send(val);
		else
			targetFalse.send(val);
	}

	/**
	 * Invoke for each value.
	 * @param val
	 * @return determines which target value will get send to.
	 */
	protected abstract boolean route(T val);
}
