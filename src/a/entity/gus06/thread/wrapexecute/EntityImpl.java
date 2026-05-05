package a.entity.gus06.thread.wrapexecute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170819";}

	private Service findExecute;
	private Service executeToRunnable;

	public EntityImpl() throws Exception
	{
		findExecute = Outside.service(this,"gus06.find.execute");
		executeToRunnable = Outside.service(this,"gus.y.convert1.executetorunnable");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof E)
			return findExecute.t(executeToRunnable.t(obj));
		if(obj instanceof Runnable)
			return findExecute.t(obj);
		if(obj instanceof Thread)
			return findExecute.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
