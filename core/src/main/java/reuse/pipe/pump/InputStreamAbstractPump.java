package reuse.pipe.pump;

import java.io.InputStream;

import reuse.pipe.Target;

public abstract class InputStreamAbstractPump<T> extends AbstractPump<T> {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(InputStreamAbstractPump.class);
	
	InputStream is;
	
	public InputStreamAbstractPump(final InputStream is, Target<T> target) {
		super(target);
		this.is = is;
	}
}
