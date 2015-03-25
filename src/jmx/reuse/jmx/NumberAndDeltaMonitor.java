package reuse.jmx;


public class NumberAndDeltaMonitor {
	public NumberAndDeltaMonitor(Number value, String objectName) {
		new NumberMonitor(value, objectName);
		new NumberMonitor(new reuse.jmx.numbers.Speed(value), objectName + ".delta");
	}
}
