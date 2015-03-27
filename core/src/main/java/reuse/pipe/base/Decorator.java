package reuse.pipe.base;

import reuse.pipe.api.Pipe;
import reuse.pipe.api.Target;

import java.io.IOException;



public class Decorator<T> extends AbstractSource<T> implements Pipe<T> {
	public Decorator(Target<T> target) {
		super(target);
	}

	@Override
	public void send(T val) throws Exception {
		feed(val);
	}

	@Override
	public void close() throws IOException {
		target.close();
	}

	@Override
	public void flush() {
		target.flush();
	}
}
