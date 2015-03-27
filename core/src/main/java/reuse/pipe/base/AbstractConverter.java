package reuse.pipe.base;

import reuse.pipe.api.Converter;
import reuse.pipe.api.Target;

import java.io.IOException;


public abstract class AbstractConverter<F, T> extends AbstractSource<T> implements Converter<F, T> {

	public AbstractConverter(Target<T> target) {
		super(target);
	}

	@Override
	public void send(F value) throws Exception {
		feed(convert(value));
	}

	protected abstract T convert(F value) throws Exception;
	
	@Override
	public void close() throws IOException {
		target.close();
	}
	
	@Override
	public void flush() {
		target.flush();
	}
}
