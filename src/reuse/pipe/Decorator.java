package reuse.pipe;


public class Decorator<T> extends AbstractSource<T> implements Pipe<T> {
	public Decorator(Target<T> target) {
		super(target);
	}

	@Override
	public void send(T val) {
		target.send(val);
	}

}
