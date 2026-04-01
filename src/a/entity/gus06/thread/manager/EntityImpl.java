package a.entity.gus06.thread.manager;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20191220";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Runnable runnable = (Runnable) obj;
		Thread1 thread = new Thread1(runnable);
		return thread;
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable runnable = (Runnable) obj;
		Thread1 thread = new Thread1(runnable);
		thread.start();
	}
	
	
	
	public class Thread1 extends Thread implements UncaughtExceptionHandler, R
	{
		private Runnable runnable;
		private long startTime;
		private long endTime;
		private Thread parent;
		private StackTraceElement[] parentSte;
		private StackTraceElement parentSte1;
		private Exception uncaught;
		
		public Thread1(Runnable runnable)
		{
			super("RUNNABLE_"+runnable.getClass().getName());
			this.runnable = runnable;
			setUncaughtExceptionHandler(this);
			
			parent = Thread.currentThread();
			parentSte = parent.getStackTrace();
			parentSte1 = findParentSte1();
		}
		
		public void run()
		{
			startTime = System.currentTimeMillis();
			runnable.run();
			endTime = System.currentTimeMillis();
		}
		
		public void uncaughtException(Thread th, Throwable w)
		{
			String message = "Uncaught exception happend in thread: "+th.getName();
			uncaught = new Exception(message,w);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("runnable")) return runnable;
			if(key.equals("startTime")) return startTime;
			if(key.equals("endTime")) return endTime;
			if(key.equals("parent")) return parent;
			if(key.equals("parentSte")) return parentSte;
			if(key.equals("parentSte1")) return parentSte1;
			if(key.equals("uncaught")) return uncaught;
			
			if(key.equals("keys")) 
			return new String[]{"runnable","startTime","endTime",
			"parent","parentSte","parentSte1","uncaught"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		private StackTraceElement findParentSte1()
		{
			for(StackTraceElement ste : parentSte)
			{
				String name = ste.getClassName();
				if(name.startsWith("gus06.entity.") && !name.startsWith("gus06.entity.gus.thread.manager."))
				return ste;
			}
			return null;
		}
	}
}