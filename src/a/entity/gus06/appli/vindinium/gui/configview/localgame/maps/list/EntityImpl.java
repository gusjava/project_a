package a.entity.gus06.appli.vindinium.gui.configview.localgame.maps.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, G, I, ActionListener {

	public String creationDate() {return "20170923";}

	public static final long LAPSE = 500;

	
	private Service dirView;
	private File storeDir;
	
	private Timer timer;
	private TimerTask task;
	
	private int fileNumber;


	public EntityImpl() throws Exception
	{
		dirView = Outside.service(this,"gus06.dir.viewer.list.listgui1");
		storeDir = (File) Outside.resource(this,"defaultdir");
		
		if(storeDir==null) throw new Exception("StoreDir is null");
		
		refresh();
		
		task = new TimerTask(){public void run() {check();}};

		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
		dirView.addActionListener(this);
	}

	
	public Object i() throws Exception
	{return dirView.i();}
	
	
	
	public void actionPerformed(ActionEvent e)
	{selected();}


	private void selected()
	{send(this,"selected()");}


	public Object g() throws Exception
	{return dirView.g();}
	
	
	
	private void check()
	{
		if(storeDir.list().length!=fileNumber) refresh();
	}
	
	
	private void refresh()
	{
		try
		{
			fileNumber = storeDir.list().length;
			dirView.p(storeDir);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}
