package reuse.pipe;


public class Decorator<T> extends Target<T> implements Source <T>{
	Target<T> target;

	public Decorator(Target<T> target) {
		super();
		this.target = target;
	}

	@Override
	public void send(T o) {
		target.send(o);
	}

	@Override
	public Target<T> getTarget() {
		return target;
	}

	@Override
	public void setTarget(Target<T> target) {
		this.target = target;
	}
}
