package reuse.pipe;

public interface Source<T> {

	public abstract Target<T> getTarget();

	public abstract void setTarget(Target<T> target);

}