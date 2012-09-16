package reuse.pipe.decorator;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class SynchronizedDecorator<T> extends Decorator<T> {

	public SynchronizedDecorator(Target<T> target) {
		super(target);
	}

	@Override
	public synchronized void send(T val) throws Exception {
		super.send(val);
	}

}
