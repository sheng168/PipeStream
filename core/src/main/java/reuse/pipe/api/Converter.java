package reuse.pipe.api;

/**
 * Converter is a Target that accepts objects of type I, 
 * converts it and acts as a Source of type O.
 *  
 * @author sheng
 *
 * @param <I>
 * @param <O>
 */
public interface Converter<I,O> extends Target<I>, Source<O> {

}
