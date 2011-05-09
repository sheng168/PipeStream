package reuse.jmx;


public class ObjectValue implements ObjectValueMBean {
	Object value;

	public ObjectValue(Object value, String objectName) {
		this.value = value;
		MBeanHelper.register(this, objectName);
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
