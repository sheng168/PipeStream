package reuse.pipe.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import reuse.pipe.AbstractSource;
import reuse.pipe.AbstractTarget;
import reuse.pipe.Target;
import reuse.util.Pair;

public class MergePoint<L, R> extends AbstractSource<Pair<L, R>> {
	private Pair<L, R> pair = new Pair<L, R>();
	
	public final Target<L> left = new AbstractTarget<L>() {
			@Override
			public void send(L val) throws Exception {
				pair.left = val;
				cb.await();
			}
		};

	public final Target<R> right = new AbstractTarget<R>() {
			@Override
			public void send(R val) throws Exception {
				pair.right = val;
				cb.await();
			}
		};

	final CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
		@Override
		public void run() {
			System.out.println("merge " + Thread.currentThread().getName());
			try {
				feed(pair);
				pair = new Pair<L, R>();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	
	public MergePoint(Target<Pair<L, R>> target) {
		super(target);
		
//		left;
//		right;
	}

	
	
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		final CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {
				System.out.println("merge " + Thread.currentThread().getName());
			}
		});
		
		for (int i = 0; i < 2; i++) {
			new Thread() {
				public void run() {
					System.out.println("await " + Thread.currentThread().getName());
					try {
						cb.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
		
		MergePoint mp;
	}
}
