package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class TapRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TapRouter.class);

	Target<T> after;
	
	public TapRouter(Target<T> before, Target<T> after) {
		super(before);
		this.after = after;
	}

	@Override
	public void send(T val) {
		super.send(val);
		after.send(val);
	}

}
