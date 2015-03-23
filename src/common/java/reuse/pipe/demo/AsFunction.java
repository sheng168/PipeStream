package reuse.pipe.demo;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import reuse.pipe.Converter;
import reuse.pipe.Source;
import reuse.pipe.Target;
import reuse.pipe.pump.SystemTimePump;
import reuse.util.Function;

public class AsFunction {

	public static<F,T> Function<F,T> asFunction(final Converter<F,T> target) {
		return asFunction(target, target);
	}

	public static<F,T> Function<F,T> asFunction(final Target<F> target, final Source<T> source) {
		return new Function<F,T>() {
			@Override
			public T apply(F input) {
				final T[] result = (T[]) new Object[1];
				try {
					source.setTarget(new Target<T>() {
						@Override
						public void send(T val) throws Exception {
							result[0] = val;
						}


						@Override
						public void close() throws IOException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void flush() {
							// TODO Auto-generated method stub
							
						}
					});
					target.send(input);
					return result[0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		};
		
	}
	
	/**
	 * probably buggy
	 * 
	 * @param <F>
	 * @param <T>
	 * @param target
	 * @param source
	 * @return
	 */
	public static<F,T> Function<F,Future<T>> asycFunction(final Target<F> target, final Source<T> source) {
		return new Function<F, Future<T>>() {
			@Override
			public Future<T> apply(F input) {
				final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();
				
				final Future<T> future = new Future<T>() {

					@Override
					public boolean cancel(boolean mayInterruptIfRunning) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean isCancelled() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean isDone() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public T get() throws InterruptedException,
							ExecutionException {
						return queue.take();
					}

					@Override
					public T get(long timeout, TimeUnit unit)
							throws InterruptedException, ExecutionException,
							TimeoutException {
						return queue.poll(timeout, unit);
					}
				};
				
				try {
					source.setTarget(new Target<T>() {
						@Override
						public void send(T val) throws Exception {
							queue.put(val);
						}


						@Override
						public void close() throws IOException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void flush() {
							// TODO Auto-generated method stub
							
						}
					});
					target.send(input);
					return future;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		};
	}

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final SystemTimePump target = new SystemTimePump(null);
		final Function<Boolean, Long> asFunction = asFunction(target);
		
		System.out.println(asFunction.apply(true));
		
		System.out.println(asycFunction(target, target).apply(true).get());
		
	}

}
