package a.entity.gus06.feature.wrap.f.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{return new SafeF((F) obj);}
	
	
	private class SafeF implements F
	{
		private F f;
		public SafeF(F f){this.f = f;}
		
		public boolean f(Object obj) throws Exception
		{
			Runnable1 ru = new Runnable1(f,obj);
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
		private F f;
		private Object input;
		private boolean output;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(F f, Object input)
		{
			this.f = f;
			this.input = input;
		}
		
		public void run()
		{
			try{output = f.f(input);}
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