package reuse.pipe;

import reuse.pipe.target.AbstractTarget;


public class NullTarget<T> extends AbstractTarget<T> implements Target<T> {
	@Override
	public void send(T value) {
		// use this class instead of null
	}

	@SuppressWarnings("rawtypes")
	public static final NullTarget t = new NullTarget();
	
	@SuppressWarnings("unchecked")
	public static<T> Target<T> get() {
		return t;
	}
}
