package a.entity.gus06.find.timertask;

import a.framework.*;
import java.util.Date;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160924";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof TimerTask) return obj;
		if(obj instanceof Runnable) return new TimerTask1((Runnable) obj);
		if(obj instanceof E) return new TimerTask2((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	


	private class TimerTask1 extends TimerTask
	{
		private Runnable r;
		public 	TimerTask1(Runnable r) {this.r = r;}
		
		public void run()
		{r.run();}
	}


	private class TimerTask2 extends TimerTask
	{
		private E e;
		public 	TimerTask2(E e) {this.e = e;}
		
		public void run()
		{execute(e);}
	}
	
	
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
