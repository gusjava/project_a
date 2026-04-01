package a.entity.gus06.sys.thread1.showmonitor.gui1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;
import java.util.Date;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

public class EntityImpl implements Entity, I, P, ComponentListener {

	public String creationDate() {return "20180124";}
	
	public static final long LAPSE = 50;


	private Service getTimer;
	private Service tabHolder;
	private Service guiInfos;
	private Service guiSTE;
	private Service guiDebug;
	
	private JPanel panel;
	
	private Thread thread;
	
	private Timer timer;
	private TimerTask task;
	

	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		guiInfos = Outside.service(this,"*gus06.sys.thread1.showmonitor.gui.infos");
		guiSTE = Outside.service(this,"*gus06.sys.thread1.showmonitor.gui.stacktrace");
		guiDebug = Outside.service(this,"*gus06.sys.thread1.showmonitor.gui.debuginfo");
		
		timer = (Timer) getTimer.g();
		
		tabHolder.v("Infos",guiInfos.i());
		tabHolder.v("Stacktrace",guiSTE.i());
		tabHolder.v("Debug",guiDebug.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(thread!=null) throw new Exception("Thread already initialized");
		thread = (Thread) obj;
		
		guiDebug.p(thread);
		guiInfos.p(thread);
		guiSTE.p(thread);
		
		task = new TimerTask(){public void run() {update();}};
		timer.schedule(task,new Date(),LAPSE);
		panel.addComponentListener(this);
	}
	
	
	private void update()
	{
		try
		{
			guiInfos.p(thread);
			guiSTE.p(thread);
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
	
	
	
	public void componentHidden(ComponentEvent e)
	{task.cancel();}
	
	public void componentMoved(ComponentEvent e) {}
	public void componentResized(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
}