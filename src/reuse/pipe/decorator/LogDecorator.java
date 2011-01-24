package reuse.pipe.decorator;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class LogDecorator<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogDecorator.class);

	public LogDecorator(Target<T> target) {
		super(target);
	}

	@Override
	public void send(T o) {
		log.debug("{}", o);
		super.send(o);
	}

}
