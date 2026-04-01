package a.entity.gus06.feature.wrap.p.asyn;

import a.framework.*;
import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190517";}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((P) obj);}
	
	
	
	private class Holder implements P, Runnable
	{
		private P p;
		private ArrayBlockingQueue queue;
		private Thread t;
		
		public Holder(P p)
		{
			this.p = p;
			queue = new ArrayBlockingQueue(100);
		
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		
		public void p(Object obj) throws Exception
		{
			try{queue.put(obj);}
			catch(InterruptedException e)
			{throw new Exception("Failed to handle object: "+obj,e);}
		}
		
		
		public void run()
		{
			while(true)
			handle(next());
		}
		
		
		private Object next()
		{
			try{return (String) queue.take();}
			catch(InterruptedException e) {}
			return null;
		}
		
		
		private void handle(Object data)
		{
			try
			{
				if(data==null) throw new Exception("Invalid null data retrieved");
				p.p(data);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handle(Object)",e);}
		}
	}
}
