package reuse.pipe.base;

import reuse.pipe.api.Target;

import java.io.IOException;


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