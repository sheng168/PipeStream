package reuse.pipe.router;

import java.io.IOException;
import java.util.Map;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

import com.google.common.base.Function;

public abstract class MapRouter<K,T> extends Decorator<T> {
	Function<T,K> func;
	Map<K,Target<T>> targets;
	
	public MapRouter(Function<T,K> valToKeyFunc, Target<T> noMatch, Map<K,Target<T>> targets) {
		super(noMatch);
		assert targets != null;
		assert targets.size() > 0;
		
		this.func = valToKeyFunc;
		this.targets = targets;
	}

	@Override
	public void send(T val) throws Exception {
		final K key = func.apply(val);
		final Target<T> target = targets.get(key);
		if (target != null)
			target.send(val);
		else
			super.send(val);
	}

	@Override
	public void flush() {
		for (Target<T> target : targets.values()) {
			target.flush();
		}
	}

	@Override
	public void close() throws IOException {
		for (Target<T> target : targets.values()) {
			target.close();
		}
	}

}
