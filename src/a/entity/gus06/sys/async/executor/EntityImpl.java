package a.entity.gus06.sys.async.executor;

import a.framework.*;
import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl implements Entity, P, Runnable {

	public String creationDate() {return "20200108";}


	private Thread t;
	private ArrayBlockingQueue queue;

	public EntityImpl() throws Exception
	{
		queue = new ArrayBlockingQueue(100);
	}
	
	
	public void p(Object obj) throws Exception
	{
		E execute = (E) obj;
		queue.put(execute);
		
		if(t==null || !t.isAlive())
		{
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
	}
	
	
	public void run()
	{
		E execute = (E) queue.poll();
		while(execute!=null)
		{
			execute(execute);
			execute = (E) queue.poll();
		}
	}
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
