package reuse.pipe.server;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.*;
import reuse.pipe.Decorator;
import reuse.pipe.Target;

public class ByteBufferCountDecorator extends Decorator<ByteBuffer> {
	
	private AtomicLong count = new AtomicLong();

	public ByteBufferCountDecorator(Target<ByteBuffer> target) {
		this("",target);
	}

	public ByteBufferCountDecorator(String name, Target<ByteBuffer> target) {
		super(target);

		new NumberAndDeltaMonitor(count, this.toString().replace("@",":type=")+name+",name=size");
	}

	@Override
	public void send(ByteBuffer val) throws Exception {
		count.addAndGet(val.limit());
		super.send(val);
	}

}
