package reuse.pipe;

import java.io.Closeable;
import java.io.Flushable;

public interface Target<T> extends Flushable, Closeable {
	public void send(T value);
	public void flush();
}
