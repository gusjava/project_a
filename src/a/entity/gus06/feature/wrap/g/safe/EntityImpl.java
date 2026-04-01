package a.entity.gus06.feature.wrap.g.safe;

import a.framework.*;
import java.lang.Thread.UncaughtExceptionHandler;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{return new SafeG((G) obj);}
	
	
	private class SafeG implements G
	{
		private G g;
		public SafeG(G g){this.g = g;}
		
		public Object g() throws Exception
		{
			Runnable1 ru = new Runnable1(g);
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
		private G g;
		private Object output;
		private Exception ex;
		private boolean over = false;
		
		public Runnable1(G g)
		{
			this.g = g;
		}
		
		public void run()
		{
			try{output = g.g();}
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