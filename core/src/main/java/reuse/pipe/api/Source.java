package reuse.pipe.api;

import java.io.Closeable;

/**
 * Source is something that has a Target.  
 * Closing a Source should also close the Target.
 * 
 * @author sheng
 *
 * @param <T>
 */
public interface Source<T> extends Closeable {

	public abstract Target<T> getTarget();

	public abstract void setTarget(Target<T> target);

}