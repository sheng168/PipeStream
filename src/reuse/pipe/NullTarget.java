package reuse.pipe;

import reuse.pipe.target.AbstractTarget;


public class NullTarget<T> extends AbstractTarget<T> implements Target<T> {
	@Override
	public void send(T value) {
		// use this class instead of null
	}
}
