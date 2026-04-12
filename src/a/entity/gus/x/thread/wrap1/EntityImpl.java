package a.entity.gus.x.thread.wrap1;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T, P {
	public String creationDate() {return "20191220";}

	public Object t(Object obj) throws Exception
	{
		if(obj == null) throw new Exception("Runnable is null");
		if(!(obj instanceof Runnable)) throw new Exception("Object is not a Runnable");

		Runnable runnable = (Runnable) obj;
		return new Thread1(runnable);
	}

	public void p(Object obj) throws Exception
	{
		Thread1 thread = (Thread1) t(obj);
		thread.start();
	}

	public class Thread1 extends Thread implements UncaughtExceptionHandler, R
	{
		private final Runnable runnable;
		private final long creationTime;

		private volatile long startTime;
		private volatile long endTime;

		private final Thread parent;
		private final StackTraceElement[] parentSte;
		private final StackTraceElement parentSte1;
		private volatile Exception uncaught;
		private volatile String state = "PENDING";		

		public Thread1(Runnable runnable)
		{
			super("RUNNABLE_" + runnable.getClass().getName());

			this.runnable = runnable;
			this.creationTime = System.currentTimeMillis();

			setUncaughtExceptionHandler(this);

			this.parent = Thread.currentThread();
			this.parentSte = parent.getStackTrace();
			this.parentSte1 = findParentSte1();
		}

		public void run()
		{
			synchronized(this)
			{
				if(!state.equals("PENDING")) return;
				
				startTime = System.currentTimeMillis();
				state = "RUNNING";
			}

			try
			{
				if(isInterrupted())
				{
					state = "INTERRUPTED";
					return;
				}
				runnable.run();
				endTime = System.currentTimeMillis();
				state = "COMPLETE";
			}
			catch(Throwable w)
			{
				handleUncaught(w);
				endTime = System.currentTimeMillis();
				state = "FAILED";
			}
		}

		private void handleUncaught(Throwable w)
		{
			uncaught = new Exception("Uncaught exception in thread: " + getName(), w);
		}

		public void uncaughtException(Thread th, Throwable w)
		{
			handleUncaught(w);
		}

		public Object r(String key) throws Exception
		{
			if(key.equals("state")) return state;
			if(key.equals("runnable")) return runnable;
			if(key.equals("creationTime")) return creationTime;
			if(key.equals("startTime")) return startTime;
			if(key.equals("endTime")) return endTime;

			if(key.equals("parent")) return parent;
			if(key.equals("parentSte")) return parentSte;
			if(key.equals("parentSte1")) return parentSte1;

			if(key.equals("uncaught")) return uncaught;

			if(key.equals("keys"))
				return new String[]{
					"state", "runnable","creationTime","startTime","endTime",
					"parent","parentSte","parentSte1","uncaught"
				};

			throw new Exception("Unknown key: " + key);
		}

		private StackTraceElement findParentSte1()
		{
			for(StackTraceElement ste : parentSte)
			{
				String name = ste.getClassName();
				if(name.startsWith("a.entity.") &&
				   !name.startsWith("a.entity.gus06.thread.manager."))
				return ste;
			}
			return null;
		}
	}
}