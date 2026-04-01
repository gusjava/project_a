package a.entity.gus06.time.handle.delayed.ms100;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}
	
	public static final long LAPSE = 100;
	


	private Service getTimer;
	private Timer timer;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new P1((P) obj);}
	
	
	
	private class P1 implements P
	{
		private P p;
		private TimerTask task;
		private Object obj;
		
		public P1(P p){this.p = p;}
		
		public void p(Object obj) throws Exception
		{
			this.obj = obj;
			
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run() {perform();}};
			timer.schedule(task,LAPSE);
		}
		
		private void perform()
		{handle(p,obj);}
	}
	
	
	
	private void handle(P p, Object obj)
	{
		try{p.p(obj);}
		catch(Exception e)
		{Outside.err(this,"handle(P,Object)",e);}
	}
}
