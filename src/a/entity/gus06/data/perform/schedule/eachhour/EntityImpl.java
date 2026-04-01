package a.entity.gus06.data.perform.schedule.eachhour;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160924";}

	public static final long LAPSE = 3600000;


	private Service getTimer;
	private Service findTimerTask;
	private Timer timer;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		findTimerTask = Outside.service(this,"gus06.find.timertask");
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		TimerTask task = (TimerTask) findTimerTask.t(obj);
		timer.scheduleAtFixedRate(task,new Date(),LAPSE);
	}
}
