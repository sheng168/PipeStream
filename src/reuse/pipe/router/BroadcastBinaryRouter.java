package reuse.pipe.router;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

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
