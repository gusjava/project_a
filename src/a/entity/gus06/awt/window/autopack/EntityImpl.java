package a.entity.gus06.awt.window.autopack;

import a.framework.*;
import java.awt.Window;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161127";}

	public static final long LAPSE = 100;
	

	private Service getTimer;
	private Timer timer;

	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		PackTask task = new PackTask((Window) obj);
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	private class PackTask extends TimerTask
	{
		private Window w;
		
		public PackTask(Window w)
		{this.w = w;}
		
		public void run()
		{w.pack();}
	}
}
