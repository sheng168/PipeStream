package reuse.pipe.source;

import reuse.pipe.Target;

public class CounterSource extends AbstractThreadedSource<Long> {
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
}
