package reuse.pipe.source;

import java.util.concurrent.TimeUnit;

import reuse.pipe.AbstractSource;
import reuse.pipe.Target;


public abstract class AbstractThreadedSource<T> extends AbstractSource<T> implements Runnable {
	public AbstractThreadedSource(Target<T> target) {
		super(target);
	}	

//	public AbstractThreadedSource(boolean start, Target<T> target) {
//		super(target);
//		
//		if (start) {
//			start();
//		}
//	}

	public AbstractThreadedSource<T> start() {
		new Thread(this, this.getClass().getSimpleName()).start();
		return this;
	}

	@Override
	public void run() {
		try {
			while (true) {
				poll();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void poll() throws Exception {
		TimeUnit.SECONDS.sleep(1);
	}
}
