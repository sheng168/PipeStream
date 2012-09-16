package reuse.pipe.android;

import reuse.pipe.Decorator;
import reuse.pipe.Target;
import android.app.Activity;

public class RunOnMainDecorator<T> extends Decorator<T> {
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RunOnMainDecorator.class);

	Activity act;

	public RunOnMainDecorator(Activity act, Target<T> target) {
		super(target);
		this.act = act;
	}

	@Override
	public void send(final T o) throws Exception {
		act.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					RunOnMainDecorator.super.send(o);
				} catch (Exception e) {
					log.error("", e);
				}
			}		
		});		
	}
}
