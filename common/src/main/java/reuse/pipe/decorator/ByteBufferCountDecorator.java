package reuse.pipe.decorator;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

import reuse.jmx.*;
import reuse.pipe.base.Decorator;
import reuse.pipe.api.Target;

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
