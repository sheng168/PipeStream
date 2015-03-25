package reuse.jmx;


public class NumberMonitor implements NumberMonitorMBean {
	Number value;

	public NumberMonitor(Number value, String objectName) {
		this.value = value;

		MBeanHelper.register(this, objectName);
	}

	@Override
	public long getLong() {
		return value.longValue();
	}

	@Override
	public int getInt() {
		return value.intValue();
	}

	@Override
	public double getDouble() {
		return value.doubleValue();
	}

	@Override
	public float getFloat() {
		return value.floatValue();
	}
}
