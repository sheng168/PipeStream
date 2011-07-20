package reuse.jmx;


public class StringValue implements StringValueMBean {
	String value;

	public StringValue(String value, String objectName) {
		this.value = value;
		MBeanHelper.register(this, objectName);
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
