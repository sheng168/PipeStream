package reuse.pipe.source;

import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;

/**
 * @deprecated
 * @author sheng
 *
 */
public class CounterSource extends AbstractThreadedSource<Long> {
	long n;
	
	public CounterSource(final long n, Target<Long> target) {
		super(target);

		this.n = n;
	}
	
	public void run() {
		try {
			for (long i = 0; i < n; i++) {
				feed(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// 90M/sec on T510
		new CounterSource(Long.MAX_VALUE, 
//			new CountDecorator<Boolean>(
//				new NullTarget<Boolean>()
//				new CounterPump(
					new CountDecorator<Long>(
//						new LogDecorator<Long>(
							new NullTarget<Long>()
//						)
					)
//				)
//			)
		).run();
	}

}
