package a.entity.gus06.data.perform.schedule.each;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160924";}


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
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		TimerTask task = (TimerTask) findTimerTask.t(o[0]);
		long t = toLong(o[1]);
		
		timer.scheduleAtFixedRate(task,new Date(),t);
	}
	
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
}
