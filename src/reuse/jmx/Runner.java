package reuse.jmx;

public class Runner implements RunnerMBean {
	Runnable runnable;
	
	public Runner(Runnable runnable, String objectName) {
		super();
		this.runnable = runnable;

		MBeanHelper.register(this, objectName);
	}

	@Override
	public void run() {
		runnable.run();
	}

}
