package reuse.jmx.numbers.operator;

public class Add extends AbstractBinaryOperator {
	private static final long serialVersionUID = 1L;

	public Add(Number left, Number right) {
		super(left, right);
	}

	@Override
	public double doubleValue() {
		return left.doubleValue() + right.doubleValue();
	}
}
