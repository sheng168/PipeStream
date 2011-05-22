package reuse.pipe.router;

import java.util.List;

import reuse.pipe.Target;

public class RoundRobinRouter<T> extends AbstractIndexRouter<T> {

	int index;
	
	public RoundRobinRouter(List<Target<T>> targets) {
		super(targets);
	}

	int sendTo() {
		int i = index;
		index = (index + 1) % targets.size();
		return i;
	}

}
