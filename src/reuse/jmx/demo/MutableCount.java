package reuse.jmx.demo;

import java.util.logging.Logger;
import java.util.prefs.Preferences;

import reuse.jmx.MutableLong;


public class MutableCount {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MutableCount.class);
	static Preferences prefs = Preferences.userNodeForPackage(MutableCount.class);
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final MutableLong count = new MutableLong();
		
		count.value = prefs.getLong("count", 0L);
//		prefs.
		log.info("starting count at {}", count.value);
		
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
						count.value++;
					}
//					Logger.getAnonymousLogger().warning("done");
				}				
			}).start();
		}
		System.out.println("start");
//		System.out.println("done " + i);
		while (true) {
			long value = count.value;
			log.info("count checkpoing {}", value);
			prefs.putLong("count", value);
			Thread.sleep(1000);
		}
	}

}


