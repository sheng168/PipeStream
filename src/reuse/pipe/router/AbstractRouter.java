package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public abstract class AbstractRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractRouter.class);

	Target<T> targetFalse;
	
	public AbstractRouter(Target<T> target, Target<T> targetFalse) {
		super(target);
		this.targetFalse = targetFalse;
	}

	@Override
	public void send(T o) {
		if (route(o))
			super.send(o);
		else
			targetFalse.send(o);
	}

	abstract boolean route(T o);
}
