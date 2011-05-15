package reuse.jmx.numbers;

/**
 * Don't use. This is just for testing. In general, should only use
 * thread-safe classes with JMX.
 * 
 * @author shengyu
 *
 */
public class MutableLongTracked extends MutableLong {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5920567619731072479L;

	long max = Long.MIN_VALUE;
	long min = Long.MAX_VALUE;
	long sum = 0;
	long count = 0;
	
	@Override
	public void setValue(long value) {
		count++;
		max = Math.max(max, value);
		min = Math.min(min, value);
		sum += value;
		
		super.setValue(value);
	}
	
	public long longAverage() {
		return sum / count;
	}

	public float floatAverage() {
		return (float)sum / count;
	}
	
	public static void main(String[] args) {
		MutableLongTracked n = new MutableLongTracked();
		n.setValue(1);
		n.setValue(2);
		
		System.out.println(n);
	}

	@Override
	public String toString() {
		return "MutableLongTracked [max=" + max + ", min=" + min + ", sum="
				+ sum + ", count=" + count + ", longAverage()=" + longAverage()
				+ ", floatAverage()=" + floatAverage() + ", getValue()="
				+ getValue() + "]";
	}
}
