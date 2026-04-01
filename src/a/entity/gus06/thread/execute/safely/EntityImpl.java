package a.entity.gus06.thread.execute.safely;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191209";}


	private Service findThread;

	public EntityImpl() throws Exception
	{
		findThread = Outside.service(this,"gus06.find.thread");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) findThread.t(obj);
		
		t.start();
		t.join();
	}
}
