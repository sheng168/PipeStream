package reuse.jmx.demo;

import reuse.jmx.LongValue;
import reuse.jmx.Runner;
import reuse.jmx.StringValue;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sheng on 3/25/15.
 */
public class HelloJmx {
    public static void main(String[] args) throws InterruptedException {
        StringValue name = new StringValue("JMX", "reuse.test:type=group,name=name");

        AtomicLong times = new AtomicLong(3);
        new LongValue(times, "reuse.test:type=group,name=times");

        new Runner("reuse.test:type=group,name=action", new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < times.longValue(); i++) {
                    System.out.println(i + " Hello " + name.getValue());
                }
            }
        });

        System.out.println("open jmx mbean");
        Thread.sleep(Long.MAX_VALUE);
    }
}
