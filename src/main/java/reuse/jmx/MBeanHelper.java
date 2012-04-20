package reuse.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class MBeanHelper {
	/**
	 * 
	 * @param mbean
	 * @param objectName "reuse.test:type=group,name=value"
	 */
	public static void register(Object mbean, String objectName) {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName name = new ObjectName(objectName);

			mbs.registerMBean(mbean, name);
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
}
