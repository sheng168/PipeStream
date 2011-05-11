package reuse.pipe;

public abstract class AbstractConverter<F, T> extends AbstractSource<T> implements Converter<F, T> {

	public AbstractConverter(Target<T> target) {
		super(target);
	}

	@Override
	public void send(F value) {
		target.send(convert(value));
	}

	protected abstract T convert(F value);
}
