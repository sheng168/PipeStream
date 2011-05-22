package reuse.pipe;

import java.io.Closeable;

public interface Source<T> extends Closeable {

	public abstract Target<T> getTarget();

	public abstract void setTarget(Target<T> target);

}