package reuse.pipe.pump;

import java.util.Iterator;

import reuse.pipe.api.Target;


public class IteratorPump<T> extends AbstractPump<T> {
	private Iterator<T> iterator;

	public IteratorPump(Iterator<T> topic, Target<T> target) {
		super(target);
		this.iterator = topic;
	}
	
	public boolean available() {
		return iterator.hasNext();
	}

	@Override
	protected T convert(Boolean value) {
		return iterator.next();
	}
}
