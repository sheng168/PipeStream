package reuse.pipe;

/**
 * Pipe is a special case of Converter that does no type conversion
 * (but it may perform value conversion).
 * 
 * @author sheng
 *
 * @param <T>
 */
public interface Pipe<T> extends Converter<T, T> {

}
