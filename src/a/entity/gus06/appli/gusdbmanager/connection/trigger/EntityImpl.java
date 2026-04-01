package a.entity.gus06.appli.gusdbmanager.connection.trigger;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150613";}


	private Service manager;
	
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusdbmanager.connection.manager");
	}



	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		String id = (String) obj;
		Object holder = manager.r(id);
		
		String status = (String) ((R)holder).r("status");
		if(status.equals(CX_STATUS.STATUS_CONNECTED) || status.equals(CX_STATUS.STATUS_CONNECTING)) return;
		
		Trigger trigger = new Trigger(holder);
		
		t = new Thread(trigger,"THREAD_"+getClass().getName());
		t.start();
	}



	
	private void perform(G g)
	{
		try{g.g();}
		catch(Exception e)
		{Outside.err(this,"perform(G)",e);}
	}
	
	
	
	
	private class Trigger implements Runnable
	{
		private Object holder;
		public Trigger(Object holder)
		{this.holder = holder;}
		
		public void run()
		{perform((G)holder);}
	}
}
