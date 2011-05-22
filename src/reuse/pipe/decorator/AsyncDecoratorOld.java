package reuse.pipe.decorator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import reuse.pipe.Decorator;
import reuse.pipe.Target;
import reuse.pipe.thread.ThreadSafe;

public class AsyncDecoratorOld<T> extends Decorator<T> implements ThreadSafe {
	BlockingQueue<T> queue = new LinkedBlockingQueue<T>(10*1000);

	public AsyncDecoratorOld(Target<T> target) {
		super(target);
		new Thread(new Runnable(){

			@Override
			public void run() {
				AsyncDecoratorOld.this.run();
			}
			
		}).start();
//		count = new MutableLong();
//		new NumberAndDeltaMonitor(count, "real.test:type=count");
	}

	@Override
	public void send(T o) {
		try {
			queue.put(o);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void run() {
		while (true) {
			try {
				T take = queue.take();
				try {
					super.send(take);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (queue.size() == 0) {
//					log.info()
					super.flush();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
