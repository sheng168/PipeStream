package reuse.jmx;

/**
 * Much be synchronized to read/write from JMX and application thread.
 * @author shengyu
 *
 */
public class StringValue implements StringValueMBean {
	String value;

	public StringValue(String value, String objectName) {
		this.value = value;
		MBeanHelper.register(this, objectName);
	}
	
	@Override
	public synchronized String getValue() {
		return value;
	}

	@Override
	public synchronized void setValue(String value) {
		this.value = value;
	}

	@Override
	public synchronized String toString() {
		return value.toString();
	}
}
