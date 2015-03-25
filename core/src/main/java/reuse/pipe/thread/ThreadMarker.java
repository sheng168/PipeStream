package reuse.pipe.thread;

/**
 * Mark a component as thread single
 * @author shengyu
 *
 */
public interface ThreadMarker {
	public interface Async extends ThreadMarker {}
	public interface Blocking extends ThreadMarker {

	}
}
