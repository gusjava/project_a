package a.entity.gus06.time.timer.unique;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191128";}
	
	public static final String TIMER_NAME = "TIMER_"+EntityImpl.class.getName();

	private Timer1 timer;
	
	public EntityImpl() throws Exception
	{timer = new Timer1(TIMER_NAME);}
	
	public Object g() throws Exception
	{return timer;}
	
	public class Timer1 extends Timer
	{
		public Timer1(String name)
		{super(name);}
		
		public void cancel()
		{
			throw new RuntimeException("Attempt to cancel unique timer: "+TIMER_NAME);
		}
		
		public void schedule(TimerTask task, Date time)
		{
			super.schedule(new SafeTimerTask(task), time);
		}
		
		public void schedule(TimerTask task, Date firstTime, long period)
		{
			super.schedule(new SafeTimerTask(task), firstTime, period);
		}
		
		public void schedule(TimerTask task, long delay)
		{
			super.schedule(new SafeTimerTask(task), delay);
		}
		
		public void schedule(TimerTask task, long delay, long period)
		{
			super.schedule(new SafeTimerTask(task), delay, period);
		}
		
		public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
		{
			super.scheduleAtFixedRate(new SafeTimerTask(task), firstTime, period);
		}
		
		public void scheduleAtFixedRate(TimerTask task, long delay, long period)
		{
			super.scheduleAtFixedRate(new SafeTimerTask(task), delay, period);
		}
	}
	
	private class SafeTimerTask extends TimerTask
	{
		private final TimerTask task;
		SafeTimerTask(TimerTask task)
		{this.task = task;}
		
		public void run()
		{
			try {task.run();}
			catch (Throwable t)
			{Outside.err(EntityImpl.this, "run()", toException(t));}
		}
	}
	
	private Exception toException(Throwable t)
	{
		if (t instanceof Exception) return (Exception) t;
		return new RuntimeException(t);
	}
}
