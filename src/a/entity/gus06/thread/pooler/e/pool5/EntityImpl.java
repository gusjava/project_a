package a.entity.gus06.thread.pooler.e.pool5;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160220";}

	public static final int NB = 5;

	private Thread[] pool;
	
	
	public EntityImpl() throws Exception
	{
		pool = new Thread[NB];
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable r = new Runnable1((E) obj);
		int index = waitForAvailable();
		
		pool[index] = new Thread(r,"THREAD_"+getClass().getName()+"_"+index);
		pool[index].start();
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
		private E ex;
		public Runnable1(E ex){this.ex = ex;}
		public void run(){execute(ex);}
	}
	
	
	private void execute(E ex)
	{
		try{ex.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
