package reuse.pipe.source;

import java.util.Iterator;

import reuse.pipe.Target;


public class IteratorSource<T> extends AbstractThreadedSource<T> {
	private Iterator<T> iterator;

	public IteratorSource(Iterator<T> topic, Target<T> target) {
		super(target);
		this.iterator = topic;
	}
	
//	@Override
	public void run() {
		while (iterator.hasNext()) {
			T value = iterator.next();
			feed(value);
		}
	}
	
}
