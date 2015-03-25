package reuse.jmx.numbers.operator;

public class Subtract extends AbstractBinaryOperator {
	private static final long serialVersionUID = 1L;

	public Subtract(Number left, Number right) {
		super(left, right);
	}

	@Override
	public double doubleValue() {
		return left.doubleValue() - right.doubleValue();
	}
}
