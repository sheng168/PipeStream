package reuse.pipe.decorator;

import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

public class SynchronizedDecorator<T> extends Decorator<T> {

	public SynchronizedDecorator(Target<T> target) {
		super(target);
	}

	@Override
	public synchronized void send(T val) throws Exception {
		super.send(val);
	}

}
