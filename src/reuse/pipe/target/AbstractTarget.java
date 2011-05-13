package reuse.pipe.target;

import java.io.IOException;

import reuse.pipe.Target;

public abstract class AbstractTarget<T> implements Target<T> {
	@Override
	public void flush() {
		// subclass can override
	}
	
	@Override
	public void close() throws IOException {
		// subclass can override
	}
}