package reuse.pipe.router;

import java.util.List;

import reuse.pipe.Target;

public class RoundRobinSwitch<T> extends IndexSwitch<T> {

	int index;
	
	public RoundRobinSwitch(List<Target<T>> targets) {
		super(targets);
	}

	int sendTo() {
		int i = index;
		index = (index + 1) % targets.size();
		return i;
	}

}
