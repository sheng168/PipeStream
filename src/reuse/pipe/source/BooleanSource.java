package reuse.pipe.source;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;

/**
 * Provide source of Boolean, but most importantly, thread cycles
 * @author shengyu
 *
 */
public class BooleanSource extends AbstractThreadedSource<Boolean> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(BooleanSource.class);

	public BooleanSource(Target<Boolean> target) {
		super(target);
	}

	public void run() {
		try {
			while (!close) {
				feed(Boolean.FALSE);
				feed(Boolean.TRUE);
			}
		} catch (Exception e) {
			log.warn("caught exception, exiting loop", e);
		}
	}
	
	public static void main(String[] args) {
		// 100M / sec on T510
		new BooleanSource(
			new CountDecorator<Boolean>(
				new NullTarget<Boolean>()
			)
		).run();
	}
}
