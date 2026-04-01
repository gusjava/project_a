package a.entity.gus06.appli.gusexplorer.scripts.startup.manager.executor1;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170103";}


	private Service executor0;
	
	public EntityImpl() throws Exception
	{
		executor0 = Outside.service(this,"gus06.appli.gusexplorer.scripts.startup.manager.executor0");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable1 r = new Runnable1(obj);
		new Thread(r,"THREAD_"+getClass().getName()).start();
	}
	
	
	
	private class Runnable1 implements Runnable
	{
		private Object src;
		public Runnable1(Object src)
		{this.src = src;}
		
		public void run()
		{executeSrc(src);}
	}
	
	
	
	private void executeSrc(Object src)
	{
		try{executor0.p(src);}
		catch(Exception e)
		{Outside.err(this,"executeSrc(Object)",e);}
	}
}