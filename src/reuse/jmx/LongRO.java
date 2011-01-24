package reuse.jmx;

import java.lang.management.*;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.*;

public class LongRO implements LongROMBean {
	AtomicLong value;

	public LongRO(AtomicLong value) {
		this.value = value;
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
	      try {
			ObjectName name = new ObjectName("com.example:type=Hello"); 

			  mbs.registerMBean(this, name);
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public long getValue() {
		return value.get();
	}

//	@Override
	public void setValue(long value) {
		this.value.set(value);
	}
}
