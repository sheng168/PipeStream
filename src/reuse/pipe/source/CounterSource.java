package reuse.pipe.source;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;


public class CounterSource extends AbstractSource<Long> implements Runnable {
	long n;
	
	public CounterSource(final long n, Target<Long> target) {
		super(target);

		this.n = n;
	}
	
	public void run() {
		for (long i = 0; i < n ; i++) {
			CounterSource.this.target.send(i);
		}
	}
	
	public void start() {
		new Thread(this, CounterSource.class.getSimpleName()).start();
	}
}
