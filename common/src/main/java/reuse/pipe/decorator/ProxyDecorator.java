package reuse.pipe.decorator;

import reuse.pipe.api.Beta;
import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

@Beta
public class ProxyDecorator<T> extends Decorator<T> {
	
	private Decorator<T> delegate;

	public ProxyDecorator(Decorator<T> delegate, Target<T> target) {
		super(target);
		set(delegate);
	}

	@Override
	public void send(T val) throws Exception {
		delegate.send(val);
	}
	
	public void set(Decorator<T> delegate) {
//		if (delegate == null)
//			return;
		this.delegate = delegate;
		this.delegate.setTarget(getTarget());
	}
}
