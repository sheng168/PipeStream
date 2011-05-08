package reuse.pipe.decorator;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

/**
 * Add delay if necessary base on time elapse from previous send
 * 
 * @author shengyu
 *
 * @param <T>
 */
public class SpacerDelayDecorator<T> extends Decorator<T> {
	private static final int NANOS_IN_MILLIS = 1000000;

	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SpacerDelayDecorator.class);

	private final long nanos;
	private long open;

	public SpacerDelayDecorator(long nanos, Target<T> target) {
		super(target);
		this.nanos = nanos;
	}

	public SpacerDelayDecorator(long millis, long nanos, Target<T> target) {
		this(millis*NANOS_IN_MILLIS + nanos, target);
	}

	@Override
	public void send(T o) {
		final long now = System.nanoTime();
		
		if (now < open) {
			long sleep = open - now;
			try {
				Thread.sleep(sleep/NANOS_IN_MILLIS, (int)(sleep%NANOS_IN_MILLIS));
			} catch (InterruptedException e) {
				log.warn("", e);
			}
		}
		
		open = now + nanos;
		super.send(o);
	}
}
