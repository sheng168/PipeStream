package reuse.jmx.numbers.operator;

public abstract class AbstractBinaryOperator extends Number {
	private static final long serialVersionUID = 1L;
	
	Number left;
	Number right;

	public AbstractBinaryOperator(Number left, Number right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public int intValue() {
		return (int) longValue();
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}
}