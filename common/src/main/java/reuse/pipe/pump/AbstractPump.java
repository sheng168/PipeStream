package reuse.pipe.pump;

import reuse.pipe.base.AbstractConverter;
import reuse.pipe.api.Target;

public abstract class AbstractPump<T> extends AbstractConverter<Boolean, T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractPump.class);

	public AbstractPump(Target<T> target) {
		super(target);
	}

}
