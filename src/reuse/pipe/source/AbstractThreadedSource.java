package reuse.pipe.source;

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
}
