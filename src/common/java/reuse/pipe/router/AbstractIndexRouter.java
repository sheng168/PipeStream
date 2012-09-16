package reuse.pipe.router;

import java.util.List;

import reuse.pipe.Target;

public abstract class AbstractIndexRouter<T> extends AbstractListRouter<T> {

	public AbstractIndexRouter(List<Target<T>> targets) {
		super(targets);
	}

	@Override
	public void send(T value) throws Exception {
		targets.get(sendTo()).send(value);
	}

	abstract int sendTo();

}