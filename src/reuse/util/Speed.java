package reuse.util;


@SuppressWarnings("serial")
public class Speed extends Number {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Speed.class);
	
	long minElapse = 1000;
	Number count;
	long prevTime;
	double prevCount;
	double prevRate;
	
	public Speed(Number count) {
		this.count = count;
		
		prevCount = count.doubleValue();
		prevTime = System.currentTimeMillis();
	}

	@Override
	public double doubleValue() {
		long t = System.currentTimeMillis();		
		long elapse = t - prevTime;
		
		if (elapse < minElapse) {
//			elapse = 1;
			log.warn("elapse = {}, using prev {}", elapse, prevRate);
			return prevRate;
		}
		double v = count.doubleValue();
		double delta = v - prevCount;
		double ratePerSec = delta / elapse * 1000;
		prevRate = ratePerSec;
		
		log.info("dv={} dt={} dv/dt={}\n", new Object[]{delta, (double)elapse, ratePerSec});

		prevCount = v;
		prevTime = t;
		
		return ratePerSec;
	}

	@Override
	public float floatValue() {
		return (float)doubleValue();
	}

	@Override
	public int intValue() {
		return (int)doubleValue();
	}

	@Override
	public long longValue() {
		return (long)doubleValue();
	}

}
