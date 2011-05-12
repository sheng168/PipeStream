package reuse.pipe.router;

import java.util.List;

import reuse.pipe.Target;

public abstract class IndexSwitch<T> extends AbstractSwitch<T> {

	public IndexSwitch(List<Target<T>> targets) {
		super(targets);
	}

	@Override
	public void send(T value) {
		targets.get(sendTo()).send(value);
	}

	abstract int sendTo();

}