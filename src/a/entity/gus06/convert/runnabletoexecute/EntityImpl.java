package a.entity.gus06.convert.runnabletoexecute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}
	
	
	public Object t(Object obj) throws Exception
	{return new E1((Runnable) obj);}
	
	
	private class E1 implements E
	{
		private Runnable runnable;
		
		public E1(Runnable runnable)
		{this.runnable = runnable;}

		public void e() throws Exception
		{startRunnable(runnable);}
	}
	
	
	private void startRunnable(Runnable runnable)
	{new Thread(runnable,"THREAD_"+getClass().getName()).start();}
}
