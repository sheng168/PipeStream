package reuse.pipe.router;

import java.util.HashSet;
import java.util.List;

import reuse.pipe.Target;

public class BroadcastRouter<T> extends AbstractListRouter<T> {
	HashSet<Target<T>> toRemove = new HashSet<Target<T>>();

	public BroadcastRouter(List<Target<T>> targets) {
		super(targets);
	}

	@Override
	public void send(T value) throws Exception {
		if (toRemove.size() > 0) {
			targets.removeAll(toRemove);
			toRemove.clear();
		}
		
		for (Target<T> t : targets) {
			t.send(value);
		}
	}
	
	public void remove(Target<T> t) {
		toRemove.add(t);
	}
}