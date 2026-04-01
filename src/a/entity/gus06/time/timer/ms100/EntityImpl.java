package a.entity.gus06.time.timer.ms100;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl extends S1 implements Entity {

	public String creationDate() {return "20150616";}

	public static final long LAPSE = 100;


	private Service getTimer;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		
		TimerTask task = new TimerTask(){
			public void run() {perform();}
		};
		
		Timer timer = (Timer) getTimer.g();
		timer.scheduleAtFixedRate(task,new Date(),LAPSE);
	}

	
	private void perform()
	{send(this,"perform()");}
}
