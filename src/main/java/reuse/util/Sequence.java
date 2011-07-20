package reuse.util;

import java.util.Iterator;

public class Sequence implements Iterator<Long>, Iterable<Long> {
	final long from, to;
	long i;
	
	public Sequence(long from, long to) {
		super();
		this.i = this.from = from;
		this.to = to;
	}

	public long getAndInc() {
		long r = i;
		
		if (i == to)
			i = from;
		else
			i++;
		
		return r;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Integer.toHexString(Integer.MAX_VALUE));
		Sequence seq = new Sequence(Integer.MAX_VALUE-5, Integer.MAX_VALUE);
		for (int i = 0; i < 10; i++) {
			System.out.println(seq.getAndInc());	
		}
	}

	@Override
	public boolean hasNext() {
		return i < to;
	}

	@Override
	public Long next() {
		return getAndInc();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<Long> iterator() {
		return this;
	}

}
