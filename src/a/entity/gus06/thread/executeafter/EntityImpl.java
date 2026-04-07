package a.entity.gus06.thread.executeafter;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180318";}

	

	private Service findThread;
	private Service findExecute;
	private Service buildThread;

	public EntityImpl() throws Exception
	{
		findThread = Outside.service(this,"gus06.find.thread");
		findExecute = Outside.service(this,"gus06.find.execute");
		buildThread = Outside.service(this,"gus.x.thread.wrap1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Thread t = (Thread) findThread.t(o[0]);
		E execute = (E) findExecute.t(o[1]);
		
		Holder holder = new Holder(t,execute);
		Thread t1 = (Thread) buildThread.t(holder);
		
		while(t1.isAlive() && t1.getState()!=Thread.State.WAITING)
		{
			try{Thread.currentThread().sleep(5);}
			catch(InterruptedException e){}	
		}
	}
	
	
	private class Holder implements Runnable
	{
		private Thread t;
		private E execute;
		
		public Holder(Thread t, E execute)
		{
			this.t = t;
			this.execute = execute;
		}
		
		public void run()
		{perform(t,execute);}
	}
	
	
	private void perform(Thread t, E execute)
	{
		try
		{
			t.join();
			execute.e();
		}
		catch(Exception e)
		{Outside.err(this,"perform(Thread,E)",e);}
	}
}