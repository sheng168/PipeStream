package reuse.pipe.decorator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class AsyncDecorator<T> extends Decorator<T> {
	BlockingQueue<T> queue = new LinkedBlockingQueue<T>(10*1000);
//	private MutableLong count;

	public AsyncDecorator(Target<T> target) {
		super(target);
		new Thread(new Runnable(){

			@Override
			public void run() {
				AsyncDecorator.this.run();
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
				super.send(take);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
