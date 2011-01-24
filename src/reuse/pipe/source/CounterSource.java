package reuse.pipe.source;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;


public class CounterSource extends AbstractSource {
	public CounterSource(final long n, Target target) {
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
