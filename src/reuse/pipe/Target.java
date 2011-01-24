package reuse.pipe;

public class Target<T> {
	public static final Target<?> NOOP = new Target<Object>();
	
	public void send(T o) {
		
	}
}
