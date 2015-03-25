package reuse.util;

public class StopWatch {

	public static long time(Runnable r, int count) {
		long nano = System.nanoTime();
		for (int i = 0; i < count; i++) {
	    	r.run();
		}
		nano = System.nanoTime() - nano;
		System.out.println("took " + nano + "ns");
		System.out.println("took " + (nano/1000000.0) + "ms");
		return nano;
	}

}
