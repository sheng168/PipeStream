package reuse.pipe;


public class AbstractSource<T> implements Source<T> {

	protected Target<T> target;

	public AbstractSource(Target<T> target) {
		super();
		this.target = target;
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