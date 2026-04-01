package a.entity.gus06.sys.runtask1.manager;

import a.framework.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.List;


public class EntityImpl extends S1 implements Entity, ActionListener, V, R {

	public String creationDate() {return "20150602";}
	
	public static final long LAPSE = 300;
	
	public static final String STATE_PENDING = "pending";
	public static final String STATE_RUNNING = "running";
	public static final String STATE_COMPLETE = "complete";
	public static final String STATE_FAILED = "failed";
	public static final String STATE_INTERRUPTED = "interrupted";


	private Service factory;
	private Service buildThread;

	
	private List queue;
	private Map threads;
	
	private Timer timer;
	private TimerTask task;
	
	private int[] numbers;
	
	

	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"factory#gus.sys.runtask1.holder");
		buildThread = Outside.service(this,"gus06.thread.manager");
		
		queue = new ArrayList();
		threads = new HashMap();
		
		task = new TimerTask() {public void run() {check();}};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("queue")) return queue;
		if(key.equals("numbers")) return numbers();
		
		if(key.equals("keys")) return new String[]{"queue","numbers"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		V holder = (V) factory.g();
		holder.v("path",key);
		holder.v("task",obj);
		
		((S) holder).addActionListener(this);
		
		synchronized(queue)
		{queue.add(holder);}
		
		newTaskQueued();
	}
	
	
	
	
	private void check()
	{
		synchronized(queue)
		{
			int number = queue.size();
			for(int i=0;i<number;i++)
			{handle((R) queue.get(i));}
		}
	}
	
	
	
	
	
	
	private void handle(R holder)
	{
		try
		{
			String path = (String) holder.r("path1");
			if(path==null || isBusy(path)) return;
			
			Thread t = (Thread) buildThread.t(holder);
			threads.put(path,t);
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"handle(R)",e);}
	}
	
	
	
	
	
	
	
	
	
	private int[] numbers() throws Exception
	{
		if(numbers==null)
			numbers = initNumbers();
		return numbers;
	}
	
	
	
	private int[] initNumbers() throws Exception
	{
		int nb_pending = 0;
		int nb_running = 0;
		int nb_complete = 0;
		int nb_failed = 0;
		int nb_interrupted = 0;
		
		synchronized(queue)
		{
			int number = queue.size();
			for(int i=0;i<number;i++)
			{
				R r = (R) queue.get(i);
				String state = (String) r.r("state");
				
				switch(state) {
					case STATE_PENDING: nb_pending++;break;
					case STATE_RUNNING: nb_running++;break;
					case STATE_COMPLETE: nb_complete++;break;
					case STATE_FAILED: nb_failed++;break;
					case STATE_INTERRUPTED: nb_interrupted++;break;
				}
			}
		}
		return new int[]{
			nb_pending,
			nb_running,
			nb_complete,
			nb_failed,
			nb_interrupted};
	}
	
	
	
	
	


	public void actionPerformed(ActionEvent e)
	{
		numbers = null;
		stateChanged();
	}
	
	
	
	
	private boolean hasThread(String path)
	{return threads.containsKey(path);}
	
	private Thread thread(String path)
	{return (Thread) threads.get(path);}
	
	private boolean isBusy(String path)
	{return hasThread(path) && thread(path).isAlive();}
	
	
	
	private void newTaskQueued()
	{send(this,"newTaskQueued()");}
	
	private void stateChanged()
	{send(this,"stateChanged()");}
}