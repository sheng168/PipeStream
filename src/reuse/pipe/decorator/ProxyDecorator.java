package reuse.pipe.decorator;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class ProxyDecorator<T> extends Decorator<T> {
	
	private Decorator<T> delegate;

	public ProxyDecorator(Decorator<T> delegate, Target<T> target) {
		super(target);
		set(delegate);
	}

	@Override
	public void send(T val) {
		delegate.send(val);
	}
	
	public void set(Decorator<T> delegate) {
//		if (delegate == null)
//			return;
		this.delegate = delegate;
		this.delegate.setTarget(getTarget());
	}
}
