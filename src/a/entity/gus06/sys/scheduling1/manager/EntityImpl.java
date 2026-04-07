package a.entity.gus06.sys.scheduling1.manager;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, V, R, P, E {

	public String creationDate() {return "20180118";}
	
	public static final long DEFAULT_LAPSE = 900;
	
	public static final String KEY_CURRENT_DATE = "current_date";
	public static final String KEY_PREVIOUS_DATE = "previous_date";
	public static final String KEY_LAST_DATE = "last_date";
	public static final String KEY_LAST_RESULT = "last_result";
	public static final String KEY_ID = "id";
	
	
	private Service checkCurrent;
	private Service randomId;
	private Service findTimer;
	private Service buildThread;
	
	private List list;
	private Map running;
	
	private P executor;
	private P persister;
	
	private long lapse = DEFAULT_LAPSE;
	
	private Timer timer;
	private Date date;
	private Date date0;



	public EntityImpl() throws Exception
	{
		checkCurrent = Outside.service(this,"gus06.sys.scheduling1.checkcurrent");
		randomId = Outside.service(this,"gus06.data.generate.string.random.number10");
		findTimer = Outside.service(this,"gus06.time.timer.unique");
		buildThread = Outside.service(this,"gus.x.thread.wrapper1");
		
		list = new ArrayList();
		running = new HashMap();
		timer = (Timer) findTimer.g();
	}
	
	
	public void e() throws Exception
	{
		if(isStarted()) throw new Exception("Scheduling is already started");
		if(list.isEmpty()) return;
		
		TimerTask task = new TimerTask(){public void run() {perform();}};
		timer.schedule(task,new Date(),lapse);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(isStarted()) throw new Exception("Scheduling is already started");
		
		Map map = new HashMap((Map) obj);
		map.put(KEY_ID,randomId.g());
		list.add(map);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("executor")) {executor = (P) obj;return;}
		if(key.equals("persister")) {persister = (P) obj;return;}
		if(key.equals("lapse")) {lapse = ((Long) obj).longValue();return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list")) return list;
		if(key.equals("running")) return running;
		if(key.equals("lapse")) return lapse;
		
		if(key.equals("keys")) return new String[]{"list","running","lapse"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void perform()
	{
		if(executor==null) return;
		
		
		synchronized(running)
		{
			date0 = date;
			date = new Date();
			
			for(int i=0;i<list.size();i++)
			{
				Map map = (Map) list.get(i);
				if(!isRunning(map)) runMap(map);
			}
		}
		updated();
	}
	
	
	private void runMap(Map map)
	{
		try
		{
			if(!isCurrent(map)) return;
			
			running.put(map.get(KEY_ID),map);
			
			map.put(KEY_CURRENT_DATE,date);
			map.put(KEY_PREVIOUS_DATE,date0);
			
			MapRunner runner = new MapRunner(map);
			buildThread.p(runner);
		}
		catch(Exception e)
		{Outside.err(this,"runMap(Map)",e);}
	}
	
	
	private class MapRunner implements Runnable
	{
		private Map map;
		public MapRunner(Map map) {this.map = map;}
		
		public void run()
		{
			
			Object result = execute(map);
			
			map.put(KEY_LAST_RESULT,result);
			map.put(KEY_LAST_DATE,map.get(KEY_CURRENT_DATE));
			
			map.remove(KEY_CURRENT_DATE);
			map.remove(KEY_PREVIOUS_DATE);
			
			persist(map);
			
			synchronized(running)
			{running.remove(map.get(KEY_ID));}
			
			updated();
		}
	}
	
	
	
	private boolean isRunning(Map map)
	{
		return running.containsKey(map.get(KEY_ID));
	}
	
	private boolean isCurrent(Map map)
	{
		try{return checkCurrent.f(new Object[]{map,date,date0});}
		catch(Exception e){Outside.err(this,"isCurrent(Map)",e);}
		return false;
	}
	
	
	
	private void updated()
	{send(this,"updated()");}
	
	
	
	
	private Object execute(Map map)
	{
		try
		{
			executor.p(map);
			return "DONE";
		}
		catch(Exception e)
		{
			Outside.err(this,"execute(Map)",e);
			return e;
		}
	}
	
	
	private void persist(Map map)
	{
		try{if(persister!=null) persister.p(map);}
		catch(Exception e){Outside.err(this,"persist(Map)",e);}
	}
	
	
	private boolean isStarted()
	{return timer!=null;}
}