package a.entity.gus06.thread.pooler.pobj.pool5;

import a.framework.*;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20141013";}

	public static final int NB = 5;

	private Thread[] pool;
	private P handler;
	
	
	public EntityImpl() throws Exception
	{
		pool = new Thread[NB];
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable r = new Runnable1(obj);
		int index = waitForAvailable();
		
		pool[index] = new Thread(r,"THREAD_"+getClass().getName()+"_"+index);
		pool[index].start();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("handler")) {handler = (P) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private int waitForAvailable()
	{
		int i = 0;
		while(!isAvailable(pool[i]))
		{i = i==NB-1?0:i+1;sleep1();}
		return i;
	}
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(Exception e){Outside.err(this,"sleep1()",e);}
	}
	
	
	private boolean isAvailable(Thread t)
	{return t==null || !t.isAlive();}
	
	
	private class Runnable1 implements Runnable
	{
		private Object obj;
		public Runnable1(Object obj){this.obj = obj;}
		public void run(){handle(obj);}
	}
	
	
	private void handle(Object obj)
	{
		try
		{
			if(handler==null) throw new Exception("Handler not initialized yet");
			handler.p(obj);
		}
		catch(Exception e)
		{Outside.err(this,"handle(Object)",e);}
	}
}
