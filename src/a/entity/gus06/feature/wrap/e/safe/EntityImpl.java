package a.entity.gus06.feature.wrap.e.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{return new SafeE((E) obj);}
	
	
	private class SafeE implements E
	{
		private E e;
		public SafeE(E e){this.e = e;}
		
		public void e() throws Exception
		{
			Runnable1 ru = new Runnable1(e);
			Thread th = new Thread(ru);
			
			th.setUncaughtExceptionHandler(ru);
			th.start();
			th.join();
			
			if(ru.ex!=null) throw ru.ex;
			if(!ru.over) throw new Exception("Safe thread interrupted (uncaught exception not handled...)");
		}
	}
	
	
	
	private class Runnable1 implements Runnable, UncaughtExceptionHandler
	{
		private E e;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(E e)
		{
			this.e = e;
		}
		
		public void run()
		{
			try{e.e();}
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