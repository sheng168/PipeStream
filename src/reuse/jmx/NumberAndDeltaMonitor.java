package reuse.jmx;


public class NumberAndDeltaMonitor {
	public NumberAndDeltaMonitor(Number value, String objectName) {
		new NumberMonitor(value, objectName);
		new NumberMonitor(new reuse.util.Speed(value), objectName + ".delta");
	}
}
