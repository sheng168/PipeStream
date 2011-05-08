package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public abstract class AbstractRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractRouter.class);

	Target<T> targetFalse;
	
	public AbstractRouter(Target<T> targetTrue, Target<T> targetFalse) {
		super(targetTrue);
		this.targetFalse = targetFalse;
	}

	@Override
	public void send(T val) {
		if (route(val))
			super.send(val);
		else
			targetFalse.send(val);
	}

	protected abstract boolean route(T val);
}
