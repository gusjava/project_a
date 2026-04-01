package a.entity.gus06.time.execute.delayed.ms100;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200212";}
	
	public static final long LAPSE = 100;
	


	private Service getTimer;
	private Timer timer;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new E1((E) obj);}
	
	
	
	private class E1 implements E
	{
		private E e;
		private TimerTask task;
		
		public E1(E e){this.e = e;}
		
		public void e() throws Exception
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run() {perform();}};
			timer.schedule(task,LAPSE);
		}
		
		private void perform()
		{execute(e);}
	}
	
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
