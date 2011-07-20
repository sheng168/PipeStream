package reuse.pipe.source.test;

import reuse.pipe.Decorator;
import reuse.pipe.NullTarget;
import reuse.pipe.Target;
import reuse.pipe.decorator.CountDecorator;
import reuse.pipe.source.AbstractSource;
import reuse.util.StopWatch;

public class TestSource extends AbstractSource {
	public TestSource(Target target) {
		super(target);

		for (int i = 0; i < 2; i++) {
			
			this.target = new Decorator(this.target);
		}
			
		this.target = new CountDecorator(this.target);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final TestSource source = new TestSource(new NullTarget());
		final Object o = 1;
		final long n = (long) Math.pow(10, 11);
		
		System.out.println(n);
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (long i = 0; i < n ; i++) {
					//source.target.send(o);
				}
			}
		};
		
		new reuse.jmx.Runner("real.test:type=action", r);
		new reuse.jmx.Runner("real.test:name=hello,type=say", new Runnable(){
				@Override
				public void run() {
					System.out.println("hello" + Thread.currentThread());
				}});
		StopWatch.time(r, 1);
	}

}
