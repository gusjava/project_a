package a.entity.gus06.find.execute;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170525";}


	private Service handleAction;
	private Service handleRunnable;
	private Service handleThread;
	private Service waitForMilli;
	private Service waitFor;
	
	public EntityImpl() throws Exception
	{
		handleAction = Outside.service(this,"gus06.convert.actiontoexecute");
		handleRunnable = Outside.service(this,"gus06.convert.runnabletoexecute");
		handleThread = Outside.service(this,"gus06.convert.threadtoexecute");
		waitForMilli = Outside.service(this,"gus06.time.execute.waitfor.ms");
		waitFor = Outside.service(this,"gus06.time.execute.waitfor");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof E) return obj;
		
		if(obj instanceof Action) return handleAction.t(obj);
		if(obj instanceof Thread) return handleThread.t(obj);
		if(obj instanceof Runnable) return handleRunnable.t(obj);
		if(obj instanceof Integer) return waitForMilli.t(obj);
		if(obj instanceof Long) return waitForMilli.t(obj);
		if(obj instanceof String) return waitFor.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
