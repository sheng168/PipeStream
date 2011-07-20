package reuse.jmx.demo;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/*
 * -server 22M 
 * -client vm 80M
 * very strange
 */
public class Count {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final AtomicLong count = new AtomicLong();
		new reuse.jmx.NumberMonitor(count, "real.test:type=count");
		new reuse.jmx.NumberMonitor(new reuse.util.Speed(count), "real.test:type=speed");
		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					Logger.getAnonymousLogger().warning("start");
					long i = 0;
					long n = 1000000000L*10;
					for (; true; i++) {
						System.nanoTime();
						count.incrementAndGet();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					Logger.getAnonymousLogger().warning("done");
				}				
			}).start();
		}
		System.out.println("start");
//		System.out.println("done " + i);
	}

}


