package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class TapRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TapRouter.class);

	Target<T> targetCC;
	
	public TapRouter(Target<T> target, Target<T> targetCC) {
		super(target);
		this.targetCC = targetCC;
	}

	@Override
	public void send(T o) {
		super.send(o);
		targetCC.send(o);
	}

}
