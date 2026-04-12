package a.entity.gus.y.timer2.send.ms500;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import a.framework.*;

public class EntityImpl extends S1 implements Entity {
	public String creationDate() {return "20240104";}

	public static final long LAPSE = 500;

	private Service getTimer;

	public EntityImpl() throws Exception {
		getTimer = Outside.service(this, "gus.y.timer2.unique");

		TimerTask task = new TimerTask() {
			public void run() {
				perform();
			}
		};

		Timer timer = (Timer) getTimer.g();
		timer.scheduleAtFixedRate(task, new Date(), LAPSE);
	}

	private void perform() {
		send(this, "perform()");
	}
}