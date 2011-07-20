package reuse.jmx;

import java.util.concurrent.atomic.AtomicLong;

public class LongValue implements LongValueMBean {
	AtomicLong value;

	public LongValue(AtomicLong value, String objectName) {
		this.value = value;
		MBeanHelper.register(this, objectName);
	}
	
	@Override
	public long getValue() {
		return value.get();
	}

	@Override
	public void setValue(long value) {
		this.value.set(value);
	}
}
