package reuse.pipe.decorator;

import org.slf4j.Logger;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

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
	public void send(T o) {
		log.debug(logMsg, o);
		super.send(o);
	}

}
