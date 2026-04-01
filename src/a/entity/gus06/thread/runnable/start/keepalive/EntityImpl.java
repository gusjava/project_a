package a.entity.gus06.thread.runnable.start.keepalive;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180228";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.runnable");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable r = (Runnable) find.t(obj);
		Thread1 t = new Thread1(r);
		t.start();
	}
	
	public Object t(Object obj) throws Exception
	{
		Runnable r = (Runnable) find.t(obj);
		Thread1 t = new Thread1(r);
		t.start();
		return t;
	}
	
	
	
	private class Thread1 extends Thread
	{
		private Runnable runnable;
		private Thread t;
		
		public Thread1(Runnable runnable)
		{
			super("THREAD1_"+runnable.getClass().getName());
			this.runnable = runnable;
		}
		
		public void run()
		{
			while(true)
			{
				t = new Thread(runnable,"THREAD_"+runnable.getClass().getName());
				t.start();
				
				while(t.isAlive())
				{
					try{t.join();}
					catch(InterruptedException e){}
				}
			}
		}
	}
}
