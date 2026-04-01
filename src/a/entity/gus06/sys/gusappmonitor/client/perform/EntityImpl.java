package a.entity.gus06.sys.gusappmonitor.client.perform;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190528";}

	public static final String AWT = "AWT";

	private Service findEQ;
	private Thread eqThread;


	public EntityImpl() throws Exception
	{
		findEQ = Outside.service(this,"gus06.thread.awteventqueue.find");
		eqThread = (Thread) findEQ.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		P p = (P) obj;
		
		Thread.State state = eqThread.getState();
		if(state==Thread.State.BLOCKED 
			|| state==Thread.State.TIMED_WAITING 
			|| state==Thread.State.TERMINATED)
		{
			p.p(AWT+":"+state.name());
		}
	}
}
