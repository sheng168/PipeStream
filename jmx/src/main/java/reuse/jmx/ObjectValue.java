package reuse.jmx;


public class ObjectValue implements ObjectValueMBean {
	Object value;

	public ObjectValue(Object value, String objectName) {
		this.value = value;
		MBeanHelper.register(this, objectName);
	}
	
	@Override
	public synchronized Object getValue() {
		return value;
	}

	@Override
	public synchronized void setValue(Object value) {
		this.value = value;
	}

	@Override
	public synchronized String toString() {
		return value.toString();
	}
}
