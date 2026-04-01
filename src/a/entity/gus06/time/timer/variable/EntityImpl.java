package a.entity.gus06.time.timer.variable;

import a.framework.*;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl extends S1 implements Entity, E, V, R, Runnable {

	public String creationDate() {return "20150626";}


	public static final long LAPSE = 10000;
	

	private Service getTimer;
	
	private Timer timer;
	private TimerTask task;
	private Map prop;
	
	private String propKey = null;
	private long defaultLapse = -1;
	private long lapse = -1;
	
	private Thread t;
	
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		prop = (Map) Outside.resource(this,"prop");
		timer = (Timer) getTimer.g();
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("propKey"))
		{propKey = (String) obj;return;}
		
		if(key.equals("defaultLapse"))
		{defaultLapse = Long.parseLong((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("propKey")) return propKey;
		if(key.equals("defaultLapse")) return ""+defaultLapse;
		if(key.equals("lapse")) return ""+lapse;
		
		if(key.equals("keys")) return new String[]{"propKey","defaultLapse","lapse"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	public void e() throws Exception
	{
		scheduleNextTask();
	}
	
	
	
	
	private void scheduleNextTask()
	{
		cancelTask();
		
		lapse = getLapse();
		task = new TimerTask(){
			public void run()
			{
				if(t==null || !t.isAlive()) start();
				scheduleNextTask();
			}
		};
		timer.schedule(task,lapse);
	}
	
	
	
	
	private void cancelTask()
	{
		if(task==null) return;
		
		task.cancel();
		task = null;
		timer.purge();
	}
	
	
	
	private long getLapse()
	{
		if(propKey==null) return getDefaultLapse();
		if(!prop.containsKey(propKey)) return getDefaultLapse();
		
		String value = (String) prop.get(propKey);
		return Long.parseLong(value);
	}

	private long getDefaultLapse()
	{return defaultLapse>0?defaultLapse:LAPSE;}


	
	
	
	
	private void start()
	{
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	public void run()
	{perform();}
	
	private void perform()
	{send(this,"perform()");}
}
