package a.entity.gus06.feature.wrap.i.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{return new SafeI((I) obj);}
	
	
	private class SafeI implements I
	{
		private I i;
		public SafeI(I i){this.i = i;}
		
		public Object i() throws Exception
		{
			Runnable1 ru = new Runnable1(i);
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
		private I i;
		private Object output;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(I i)
		{
			this.i = i;
		}
		
		public void run()
		{
			try{output = i.i();}
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