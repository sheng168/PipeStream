package reuse.jmx;

/**
 * Don't use. This is just for testing. In general, should only use
 * thread-safe classes with JMX.
 * 
 * @author shengyu
 *
 */
public class MutableLong extends Number {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5920567619731072479L;
	
	public long value;
	
	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return value;
	}

}
