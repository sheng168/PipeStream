package reuse.pipe.thread;

import org.junit.runner.*;
import org.junit.runners.Suite;

import reuse.util.PairTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MergePointTest.class,
	PairTest.class
})

public class AllTests {}
