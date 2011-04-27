package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class TapRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TapRouter.class);

	Target<T> target2;
	
	public TapRouter(Target<T> target, Target<T> target2) {
		super(target);
		this.target2 = target2;
	}

	@Override
	public void send(T o) {
		super.send(o);
		target2.send(o);
	}

}
