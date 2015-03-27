package reuse.pipe.api;

import java.io.Closeable;
import java.io.Flushable;

/**
 * This is the most important interface that everything else is built on.
 * It extends Flushable to allow send to do some buffering for performance
 * and Closeable for resource cleanup.
 * 
 * @author sheng
 *
 * @param <T>
 */
public interface Target<T> extends Flushable, Closeable {
	public void send(T value) throws Exception;
	public void flush();
}
