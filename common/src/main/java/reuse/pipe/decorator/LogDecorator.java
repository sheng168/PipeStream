package reuse.pipe.decorator;

import org.slf4j.Logger;

import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

public class LogDecorator<T> extends Decorator<T> {
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogDecorator.class);

	private String logMsg = "{}";

	public LogDecorator(Logger log, String logMsg, Target<T> target) {
		super(target);
		this.log = log;
		this.logMsg = logMsg;
	}

	public LogDecorator(String logMsg, Target<T> target) {
		super(target);
		this.logMsg = logMsg;
	}

	public LogDecorator(Target<T> target) {
		super(target);
	}

	@Override
	public void send(T o) throws Exception {
		log.debug(logMsg, o);
		super.send(o);
	}

}
