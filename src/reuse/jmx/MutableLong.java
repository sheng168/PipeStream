package reuse.jmx;

public class MutableLong extends Number {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5920567619731072479L;
	
	public long value;
	
	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return (int) value;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
