package reuse.pipe;

import java.io.IOException;

import reuse.pipe.source.AbstractSource;

public abstract class AbstractConverter<F, T> extends AbstractSource<T> implements Converter<F, T> {

	public AbstractConverter(Target<T> target) {
		super(target);
	}

	@Override
	public void send(F value) {
		target.send(convert(value));
	}

	protected abstract T convert(F value);
	
	@Override
	public void close() throws IOException {
		target.close();
	}
	
	@Override
	public void flush() {
		target.flush();
	}
}
