package a.entity.gus06.feature.wrap.t.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191218";}
	
	
	private Service thM;
	
	public EntityImpl() throws Exception
	{thM = Outside.service(this,"gus06.thread.manager");}

	
	public Object t(Object obj) throws Exception
	{return new SafeT((T) obj);}
	
	
	private class SafeT implements T
	{
		private T t;
		public SafeT(T t){this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			Runnable1 ru = new Runnable1(t,obj);
			Thread th = (Thread) thM.t(ru);
			
			th.setUncaughtExceptionHandler(ru);
			th.start();
			th.join();
			
			if(ru.ex!=null) throw ru.ex;
			if(!ru.over) throw new Exception("Safe thread interrupted (uncaught exception not handled...)");
			
			return ru.output;
		}
	}
	
	
	
	private class Runnable1 implements Runnable, UncaughtExceptionHandler
	{
		private T t;
		private Object input;
		private Object output;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(T t, Object input)
		{
			this.t = t;
			this.input = input;
		}
		
		public void run()
		{
			try{output = t.t(input);}
			catch(Exception ex){this.ex = ex;}
			over = true;
		}
		
		public void uncaughtException(Thread th, Throwable w)
		{
			String message = "Uncaught exception happend in thread: "+th.getName();
			ex = new Exception(message,w);
		}
	}
}