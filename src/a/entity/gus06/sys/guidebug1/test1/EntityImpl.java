package a.entity.gus06.sys.guidebug1.test1;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class EntityImpl implements Entity {

	public String creationDate() {return "20160511";}
	
	public static final long LAPSE = 50;
	public static final String AWTEVENTTHREAD = "AWT-EventQueue-0";
	public static final int LIMIT = 5;


	private Timer timer;
	private TimerTask task;
	
	private int index = 0;
	

	public EntityImpl() throws Exception
	{
		task = new TimerTask(){
			public void run() {check();}
		};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	
	
	
	private void check()
	{
		Thread t = getAWTEventDispatchingThread();
		if(t.getState()==Thread.State.BLOCKED)
		{
			index++;
			if(index>LIMIT) AWTEventDispatchingThread_blocked();
		}
		else
		{
			if(index>LIMIT) AWTEventDispatchingThread_unblocked();
			index=0;
		}
	}
	
	
	
	
	private Thread getAWTEventDispatchingThread()
	{
		int n = Thread.activeCount();
		Thread[] array = new Thread[n];
		Thread.enumerate(array);
		
		for(int i=0;i<n;i++)
			if(array[i].getName().equals(AWTEVENTTHREAD))
				return array[i];
		return null;
	}
	
	
	
	
	
	
	private void AWTEventDispatchingThread_blocked()
	{
		Toolkit.getDefaultToolkit().beep();
	}
	
	
	
	private void AWTEventDispatchingThread_unblocked()
	{
		
	}
}
