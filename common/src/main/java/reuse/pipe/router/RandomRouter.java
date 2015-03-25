package reuse.pipe.router;

import java.util.List;
import java.util.Random;

import reuse.pipe.Target;

public class RandomRouter<T> extends AbstractIndexRouter<T> {
	private final Random RANDOM = new Random();
	
	public RandomRouter(List<Target<T>> targets) {
		super(targets);
	}

	int sendTo() {
		return RANDOM.nextInt(targets.size());
	}

}
