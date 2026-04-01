package a.entity.gus06.find.thread;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170819";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Thread) return obj;
		if(obj instanceof Runnable) return new Thread((Runnable) obj,"THREAD_"+getClass().getName());
		if(obj instanceof E) return new Thread(new Runnable1((E) obj),"THREAD_"+getClass().getName());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Runnable1 implements Runnable
	{
		private E execute;
		
		public Runnable1(E execute)
		{this.execute = execute;}
		
		public void run()
		{execute(execute);}
	}
	
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
