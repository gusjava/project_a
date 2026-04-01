package a.entity.gus06.swing.internalframe.autopack;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JInternalFrame;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191122";}

	public static final long LAPSE = 100;
	

	private Timer timer;

	public EntityImpl() throws Exception
	{
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public void p(Object obj) throws Exception
	{
		PackTask task = new PackTask((JInternalFrame) obj);
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	private class PackTask extends TimerTask
	{
		private JInternalFrame w;
		
		public PackTask(JInternalFrame w)
		{this.w = w;}
		
		public void run()
		{w.pack();}
	}
}
