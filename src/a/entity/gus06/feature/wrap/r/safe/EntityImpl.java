package a.entity.gus06.feature.wrap.r.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{return new SafeR((R) obj);}
	
	
	private class SafeR implements R
	{
		private R r;
		public SafeR(R r){this.r = r;}
		
		public Object r(String key) throws Exception
		{
			Runnable1 ru = new Runnable1(r,key);
			Thread th = new Thread(ru);
			
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
		private R r;
		private String input;
		private Object output;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(R r, String input)
		{
			this.r = r;
			this.input = input;
		}
		
		public void run()
		{
			try{output = r.r(input);}
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