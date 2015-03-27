package reuse.pipe.base;

import reuse.pipe.api.NullTarget;
import reuse.pipe.api.Source;
import reuse.pipe.api.Target;

import java.io.IOException;



public abstract class AbstractSource<T> implements Source<T> {

	protected Target<T> target;
	protected /*volatile*/ boolean close = false;

	public AbstractSource(Target<T> target) {
		super();
		if (target == null)
			this.target = NullTarget.get();
		else
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
		target.close();
	}
}