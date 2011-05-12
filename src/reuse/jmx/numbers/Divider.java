package reuse.jmx.numbers;

public class Divider extends AbstractBinaryOperator {
	private static final long serialVersionUID = 1L;

	public Divider(Number left, Number right) {
		super(left, right);
	}

	@Override
	public double doubleValue() {
		return left.doubleValue() / right.doubleValue();
	}
}
