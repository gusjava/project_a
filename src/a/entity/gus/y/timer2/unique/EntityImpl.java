package a.entity.gus.y.timer2.unique;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import a.framework.Entity;
import a.framework.G;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240104";}
	
	public static final String TIMER_NAME = "TIMER_" + EntityImpl.class.getName();

	private Timer1 timer;

	public EntityImpl() throws Exception {
		timer = new Timer1(TIMER_NAME);
	}

	public Object g() throws Exception {
		return timer;
	}

	public class Timer1 extends Timer {
		public Timer1(String name) {
			super(name);
		}

		public void cancel() {
			throw new RuntimeException("Attempt to cancel unique timer: " + TIMER_NAME);
		}

		public void schedule(TimerTask task, Date time) {
			super.schedule(task, time);
		}

		public void schedule(TimerTask task, Date firstTime, long period) {
			super.schedule(task, firstTime, period);
		}

		public void schedule(TimerTask task, long delay) {
			super.schedule(task, delay);
		}

		public void schedule(TimerTask task, long delay, long period) {
			super.schedule(task, delay, period);
		}

		public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
			super.scheduleAtFixedRate(task, firstTime, period);
		}

		public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
			super.scheduleAtFixedRate(task, delay, period);
		}
	}
}
