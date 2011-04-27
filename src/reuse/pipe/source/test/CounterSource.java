package reuse.pipe.source.test;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;


public class CounterSource extends AbstractSource<Long> {
	public CounterSource(final long n, Target<Long> target) {
		super(target);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (long i = 0; i < n ; i++) {
					CounterSource.this.target.send(i);
				}
			}
		};
		
		new Thread(r).start();
	}
}
