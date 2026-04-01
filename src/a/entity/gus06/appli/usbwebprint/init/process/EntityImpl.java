package a.entity.gus06.appli.usbwebprint.init.process;

import a.framework.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class EntityImpl implements Entity {

	public String creationDate() {return "20140916";}

	public static final long LAPSE = 800;
	

	private Service performQueue;
	private Service performMail;

	private Timer timer;
	private TimerTask task;
	
	private Thread t_queue;
	private Thread t_mail;
	
	
	
	public EntityImpl() throws Exception
	{
		performQueue = Outside.service(this,"gus06.appli.usbwebprint.process.perform.queue");
		performMail = Outside.service(this,"gus06.appli.usbwebprint.process.perform.mail");
		
		task = new TimerTask(){public void run(){check();}};
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}

	
	
	
	
	private void check()
	{
		if(t_queue==null || !t_queue.isAlive())
		{
			Runnable r = new Runnable(){public void run() {performQueue();}};
			t_queue = startThread(r,"queue");
		}
		if(t_mail==null || !t_mail.isAlive())
		{
			Runnable r = new Runnable(){public void run() {performMail();}};
			t_mail = startThread(r,"mail");
		}
	}
	
	
	
	
	
	private Thread startThread(Runnable r, String id)
	{
		Thread t = new Thread(r,"THREAD_"+getClass().getName()+"_"+id);
		t.start();
		return t;
	}
	
	
	
	private void performQueue()
	{
		try{performQueue.e();}
		catch(Exception e){Outside.err(this,"performQueue()",e);}
	}
	
	private void performMail()
	{
		try{performMail.e();}
		catch(Exception e){Outside.err(this,"performMail()",e);}
	}
}

