package reuse.pipe;


public class Decorator<T> extends Target<T> implements Source <T>{
	Target<T> target;

	public Decorator(Target<T> target) {
		super();
		this.target = target;
	}

	@Override
	public void send(T val) {
		target.send(val);
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
