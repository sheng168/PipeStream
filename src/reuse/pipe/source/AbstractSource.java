package reuse.pipe.source;

import reuse.pipe.Source;
import reuse.pipe.Target;


public abstract class AbstractSource<T> implements Source<T> {

	protected Target<T> target;

	public AbstractSource(Target<T> target) {
		super();
		this.target = target;
	}

	@Override
	public Target<T> getTarget() {
		return target;
	}

	@Override
	public void setTarget(Target<T> target) {
		this.target = target;
	}

	protected void feed(T val) throws Exception {
		target.send(val);
	}
}