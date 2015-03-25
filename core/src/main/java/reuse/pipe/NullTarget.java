package reuse.pipe;

/**
 * This is a alternative to initializing a Target variable to null.
 * It avoid null check before using a Target variable.
 * 
 * @author sheng
 *
 * @param <T>
 */
public class NullTarget<T> extends Object implements Target<T> {
	@SuppressWarnings({ "unchecked" })
	public static<T> Target<T> get() {
		return t;
	}

	/**
	 * @deprecated use get() instead
	 */
	@SuppressWarnings("rawtypes")
	public static final NullTarget t = new NullTarget();
	
	@Override
	public void send(T value) {
		// use this class instead of null
	}

	@Override
	public void close() {
		// nothing
	}

	@Override
	public void flush() {
		// nothing
	}
}
