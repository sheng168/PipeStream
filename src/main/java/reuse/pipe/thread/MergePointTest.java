package reuse.pipe.thread;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import reuse.pipe.Target;
import reuse.pipe.decorator.AsyncDecorator;
import reuse.util.Pair;

public class MergePointTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMerge() throws Exception {
		@SuppressWarnings("unchecked")
		Target<Pair<Integer, String>> target = mock(Target.class);
		
		final MergePoint<Integer, String> mp = new MergePoint<Integer, String>(target);
		
		new AsyncDecorator<Integer>(mp.left).send(1);
		mp.right.send("hi");
		
		Pair<Integer, String> p = new Pair<Integer, String>(1,"hi");
		verify(target).send(p);
		
//		fail("Not yet implemented");
	}

//	@Test
//	public void testFeed() {
//		fail("Not yet implemented");
//	}

}
