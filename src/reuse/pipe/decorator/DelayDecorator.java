package reuse.pipe.decorator;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class DelayDecorator<T> extends Decorator<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DelayDecorator.class);

	private int nanos = 1000*1000; // 1ms

	public DelayDecorator(int nanos, Target<T> target) {
		super(target);
		this.nanos = nanos;
	}

	public DelayDecorator(Target<T> target) {
		super(target);
	}

	@Override
	public void send(T o) {
		try {
			Thread.sleep(0, nanos);
		} catch (InterruptedException e) {
			log.warn("", e);
		} finally {
			super.send(o);
		}
	}
}
