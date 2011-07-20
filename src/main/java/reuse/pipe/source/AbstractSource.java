package reuse.pipe.source;

import java.io.IOException;

import reuse.pipe.Source;
import reuse.pipe.Target;


public abstract class AbstractSource<T> implements Source<T> {

	protected Target<T> target;
	protected /*volatile*/ boolean close = false;

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

	protected final void feed(T val) throws Exception {
		target.send(val);
	}

	@Override
	public void close() throws IOException {
		close  = true;
	}
}