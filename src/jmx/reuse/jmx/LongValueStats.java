package reuse.jmx;


/**
 * Don't use. This is just for testing. In general, should only use
 * thread-safe classes with JMX.
 * 
 * @author shengyu
 *
 */
public class LongValueStats implements LongValueStatsMBean {
	/**
	 * 
	 */
	long value;
	
	long max;
	long min;
	long sum;
	long count;
	
	public LongValueStats(String objectName) {
		MBeanHelper.register(this, objectName);
		
		reset();
	}

	@Override
	public synchronized void reset() {
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		sum = 0;
		count = 0;
	}
	
	@Override
	public synchronized long getValue() {
		return value;
	}

	@Override
	public synchronized void setValue(long value) {
		count++;
		max = Math.max(max, value);
		min = Math.min(min, value);
		sum += value;
		
		this.value = value;
	}
	
	@Override
	public synchronized long getAverageAsLong() {
		return sum / count;
	}

	@Override
	public synchronized float getAverage() {
		return (float)sum / count;
	}

	@Override
	public synchronized String toString() {
		return "MutableLongTracked [max=" + max + ", min=" + min + ", sum="
				+ sum + ", count=" + count + ", longAverage()=" + getAverageAsLong()
				+ ", floatAverage()=" + getAverage() + ", getValue()="
				+ value + "]";
	}
	
	public static void main(String[] args) throws InterruptedException {
		LongValueStatsMBean n = new LongValueStats("real.test:type=group,name=value");
//		n.setValue(1);
//		n.setValue(2);
//		System.out.println(n);
		
		while (true) {
			long start = System.nanoTime();
//			Thread.sleep(1);
			long elapse = System.nanoTime() - start;
			
			n.setValue(elapse);
		}
		
	}

	public long getMax() {
		return max;
	}

	public long getMin() {
		return min;
	}

	public long getSum() {
		return sum;
	}

	public long getCount() {
		return count;
	}

	@Override
	public long getLast() {
		// TODO Auto-generated method stub
		return getValue();
	}
}
