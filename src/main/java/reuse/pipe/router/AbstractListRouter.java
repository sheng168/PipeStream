package reuse.pipe.router;

import java.io.IOException;
import java.util.List;

import reuse.pipe.Pipe;
import reuse.pipe.Target;

public abstract class AbstractListRouter<T> implements Pipe<T> {
	List<Target<T>> targets;
	
	public AbstractListRouter(List<Target<T>> targets) {
		super();
		assert targets != null;
		assert targets.size() > 0;
		
		this.targets = targets;
	}

	@Override
	public void flush() {
		for (Target<T> target : targets) {
			target.flush();
		}
	}

	@Override
	public void close() throws IOException {
		for (Target<T> target : targets) {
			target.close();
		}
	}

	@Override
	public Target<T> getTarget() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTarget(Target<T> target) {
		throw new UnsupportedOperationException();
	}

}
