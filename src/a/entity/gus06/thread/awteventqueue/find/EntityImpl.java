package a.entity.gus06.thread.awteventqueue.find;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190528";}

	public static final String AWT_EVENT_QUEUE = "AWT-EventQueue-0";

	
	private Thread awtThread;
	
	public Object g() throws Exception
	{
		if(awtThread==null)
			awtThread = awtEventQueue();
		return awtThread;
	}

	private Thread awtEventQueue() throws Exception
	{
		int count = Thread.activeCount();
		Thread[] t = new Thread[count];
		Thread.enumerate(t);
		
		for(int i=0;i<count;i++)
			if(isAwtEventQueue(t[i])) return t[i];
		throw new Exception("AWT Event Queue Thread not found");
	}
	
	private boolean isAwtEventQueue(Thread t)
	{
		if(t==null) return false;
		return t.getName().equals(AWT_EVENT_QUEUE);
	}
}
