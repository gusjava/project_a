package a.entity.gus06.time.clock.holder;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class EntityImpl extends S1 implements Entity {

	public String creationDate() {return "20150522";}

	public static final long SECOND = 1000;
	public static final long MINUTE = 60000;
	public static final long HOUR = 3600000;
	public static final long DAY = 86400000;
	

	private Timer timer;


	public EntityImpl() throws Exception
	{
		TimerTask secondTask = new TimerTask(){
			public void run() {second();}
		};
		TimerTask minuteTask = new TimerTask(){
			public void run() {minute();}
		};
		TimerTask hourTask = new TimerTask(){
			public void run() {hour();}
		};
		TimerTask dayTask = new TimerTask(){
			public void run() {day();}
		};
		
		Date startDate = new Date();
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.scheduleAtFixedRate(secondTask,startDate,SECOND);
		timer.scheduleAtFixedRate(minuteTask,startDate,MINUTE);
		timer.scheduleAtFixedRate(hourTask,startDate,HOUR);
		timer.scheduleAtFixedRate(dayTask,startDate,DAY);
	}

	
	
	private void second()
	{send(this,"second()");}
	
	private void minute()
	{send(this,"minute()");}
	
	private void hour()
	{send(this,"hour()");}
	
	private void day()
	{send(this,"day()");}

}

