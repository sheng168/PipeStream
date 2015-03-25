package reuse.jmx;


public interface LongValueStatsMBean extends LongValueMBean {
//	public abstract long getValue();
//	public abstract void setValue(long value);
	
	public long getAverageAsLong();
	public float getAverage();
	
	public void reset();
	public long getMax();
	public long getMin();
//	public long getSum();
	public long getCount();
	public long getLast();
}