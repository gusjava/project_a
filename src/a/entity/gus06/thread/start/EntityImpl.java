package a.entity.gus06.thread.start;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180106";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.thread");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) find.t(obj);
		t.start();
	}
	
	public Object t(Object obj) throws Exception
	{
		Thread t = (Thread) find.t(obj);
		t.start();
		return t;
	}
}
