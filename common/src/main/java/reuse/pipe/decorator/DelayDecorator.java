package reuse.pipe.decorator;

import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

/**
 * Add a fixed amount of delay
 * 
 * @author shengyu
 *
 * @param <T>
 */
public class DelayDecorator<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DelayDecorator.class);

	private long millis = 1;
	private int nanos = 0;

	public DelayDecorator(int nanos, Target<T> target) {
		this(0, nanos, target);
	}

	public DelayDecorator(long millis, int nanos, Target<T> target) {
		super(target);
		this.millis = millis;
		millis += nanos / 1000000;
		this.nanos = nanos % 100000;
	}

	@Override
	public void send(T o) throws Exception {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException e) {
			log.warn("", e);
		} finally {
			super.send(o);
		}
	}
}
