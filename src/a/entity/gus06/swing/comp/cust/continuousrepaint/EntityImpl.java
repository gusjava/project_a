package a.entity.gus06.swing.comp.cust.continuousrepaint;

import a.framework.*;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150604";}
	
	public static final long LAPSE = 100;
	
	
	private Service getTimer;
	private Timer timer;
	private TimerTask task;
	
	private Set set;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		set = new HashSet();
		task = new TimerTask() {public void run() {check();}};
		timer.schedule(task,new Date(),LAPSE);
	}



	public synchronized void p(Object obj) throws Exception
	{set.add((Component) obj);}
	
	
	
	private synchronized void check()
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Component c = (Component) it.next();
			c.repaint();
		}
	}
}
