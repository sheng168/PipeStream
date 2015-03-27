package reuse.pipe.router;

import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

public class BroadcastBinaryRouter<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BroadcastBinaryRouter.class);

	Target<T> after;
	
	public BroadcastBinaryRouter(Target<T> before, Target<T> after) {
		super(before);
		this.after = after;
	}

	@Override
	public void send(T val) throws Exception {
		super.send(val);
		after.send(val);
	}

}
