package a.entity.gus06.jna.mouse.queue.empty;

import a.framework.*;

import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200113";}
	
	
	
	private ArrayBlockingQueue queue;

	public EntityImpl() throws Exception
	{
		queue = new ArrayBlockingQueue(100);
	}
	
	public Object g() throws Exception
	{return queue;}
}
