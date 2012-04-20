package reuse.jmx.numbers;

import com.google.common.base.*;

/**
 * Extract a number from an object using a function.
 * @author shengyu
 *
 * @param <T>
 */
public class ObjectNumber<T> extends Number {
	private static final long serialVersionUID = 1L;
	
	private final T object;

	private Function<T, Number> f;

	public ObjectNumber(T cos, Function<T, Number> f) {
		this.object = cos;
		this.f = f;
	}

	@Override
	public long longValue() {
		return f.apply(object).longValue();
	}

	@Override
	public int intValue() {
		return f.apply(object).intValue();
	}

	@Override
	public float floatValue() {
		return f.apply(object).floatValue();
	}

	@Override
	public double doubleValue() {
		return f.apply(object).doubleValue();
	}
}
