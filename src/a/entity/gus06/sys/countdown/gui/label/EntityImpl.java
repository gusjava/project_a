package a.entity.gus06.sys.countdown.gui.label;

import a.framework.*;
import javax.swing.JLabel;

public class EntityImpl extends S1 implements Entity, I, P, V, E, Runnable {

	public String creationDate() {return "20201216";}
	
	public static final String DEFAULT_TIME_UNIT = "s";


	private Service parseDuration;
	private Service formatDuration;


	private JLabel label;
	private Thread t;
	
	private long duration = -1;
	private long startTime = -1;
	private long endTime = -1;
	
	private String timeUnit;
	
	private volatile boolean interrupted = false;
	
	

	public EntityImpl() throws Exception
	{
		parseDuration = Outside.service(this,"gus06.sys.countdown.duration.parse");
		formatDuration = Outside.service(this,"gus06.sys.countdown.duration.format");
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void e() throws Exception
	{
		if(isStopped()) start();
		else stop();
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("start")) start();
		else if(s.equals("stop")) stop();
		else throw new Exception("Unknown command: "+s);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("duration")) {initDuration(obj);return;}
		if(key.equals("timeUnit")) {initTimeUnit(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void initDuration(Object obj) throws Exception
	{
		if(!isStopped()) throw new Exception("Duration initialization can't append while running");
		duration = parseDuration(obj);
	}
	
	private void initTimeUnit(Object obj) throws Exception
	{
		if(!isStopped()) throw new Exception("Time unit initialization can't append while running");
		timeUnit = (String) obj;
	}
	
	
	private long parseDuration(Object data) throws Exception
	{
		String unit = timeUnit!=null ? timeUnit : DEFAULT_TIME_UNIT;
		Long d = (Long) parseDuration.t(new Object[]{data,unit});
		return d!=null ? d.longValue() : -1;
	}
	
	
	
	private void start() throws Exception
	{
		if(!isStopped()) return;
		if(isRunning())
		{
			try{t.join();}
			catch(InterruptedException e){}
		}
		
		if(duration==-1) throw new Exception("Duration not initialized yet");
		startTime = System.currentTimeMillis();
		endTime = startTime+duration;
		
		interrupted = false;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	private void stop()
	{
		if(isStopped()) return;
		interrupted = true;
	}
	
	
	public void run()
	{
		try
		{
			boolean done = false;
			while(!interrupted && !done)
			{
				sleep(100);
				long dt = endTime - System.currentTimeMillis();
				done = dt<=0;
				
				label.setText(formatDuration(dt));
			}
			
			if(!interrupted) over();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private boolean isStopped()
	{return t==null || !t.isAlive() || interrupted;}
	
	private boolean isRunning()
	{return t!=null && t.isAlive();}
	
	
	private String formatDuration(long dt) throws Exception
	{
		if(dt<=0) return " ";
		String unit = timeUnit!=null ? timeUnit : DEFAULT_TIME_UNIT;
		return (String) formatDuration.t(new Object[]{dt,unit});
	}
	
	
	
	private void sleep(long lapse)
	{
		try{Thread.sleep(lapse);}
		catch(InterruptedException e){}
	}
	
	
	private void over()
	{send(this,"over()");}
}