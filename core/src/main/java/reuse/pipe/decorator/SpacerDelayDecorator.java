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

	private final Number spaceMillis; // allow value to change from outside
	private long open;

	public SpacerDelayDecorator(Number millis, Target<T> target) {
		super(target);
		this.spaceMillis = millis;
	}

	@Override
	public void send(T o) throws Exception {
		final long now = System.nanoTime();
		
		if (now < open) {
			long sleep = open - now;
			log.debug("sleeping for {}ns", sleep);
			
			try {
				Thread.sleep(sleep/NANOS_IN_MILLIS, (int)(sleep%NANOS_IN_MILLIS));
			} catch (InterruptedException e) {
				log.warn("", e);
			}
		} else {
			log.debug("no delay needed");
		}
		
		open = System.nanoTime() + (long)(spaceMillis.doubleValue() * 1.0e6);
		super.send(o);
	}
}
