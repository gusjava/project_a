package a.entity.gus06.thread.timeout.perform;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180106";}


	private Service stop;

	public EntityImpl() throws Exception
	{
		stop = Outside.service(this,"gus06.thread.stop");
	}

	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E execute = (E) o[0];
		long timeout = ((Long) o[1]).longValue();
		
		if(timeout<=0) return false;
		
		Runnable1 r = new Runnable1(execute);
		Thread t = new Thread(r,"THREAD_"+getClass().getName());
		
		t.start();
		
		try{t.join(timeout);}
		catch(InterruptedException e) {}
		
		if(t.isAlive())
		{
			stop.p(t);
			return false;
		}
		
		Exception exception = r.getException();
		if(exception!=null) throw exception;
		
		return true;
	}
	
	
	
	private class Runnable1 implements Runnable
	{
		private E execute;
		private Exception exception;
		
		public Runnable1(E execute)
		{this.execute = execute;}
		
		public void run()
		{
			try{execute.e();}
			catch(Exception e)
			{exception = e;}
		}
		
		public Exception getException()
		{return exception;}
	}
}
