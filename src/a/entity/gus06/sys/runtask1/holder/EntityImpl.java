package a.entity.gus06.sys.runtask1.holder;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.Date;
import javax.swing.JComponent;


public class EntityImpl extends S1 implements Entity, V, R, E, F, Runnable {

	public String creationDate() {return "20150602";}
	
	
	public static final String STATE_FAILED = "failed";
	public static final String STATE_PENDING = "pending";
	public static final String STATE_RUNNING = "running";
	public static final String STATE_COMPLETE = "complete";
	public static final String STATE_INTERRUPTED = "interrupted";



	private Service progress;
	private Service timeIndicator;
	private Set interrupt;
	
	private String path;
	private Object task;
	private String state;
	private Date startdate;
	private Date enddate;



	public EntityImpl() throws Exception
	{
		progress = Outside.service(this,"*gus06.swing.progressbar.progress1");
		timeIndicator = Outside.service(this,"*gus06.swing.progressbar.progress1.timeleft.indicator");
		
		interrupt = Collections.synchronizedSet(new HashSet());
		state = STATE_PENDING;
		
		timeIndicator.p(progress);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("path")) {path = (String) obj;return;}
		if(key.equals("task")) {task = obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void e() throws Exception
	{
		state = STATE_INTERRUPTED;
		interrupt.add("*");
		timeIndicator.e();
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		return state.equals(obj);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("path")) return path;
		if(key.equals("path1")) return isPending()?path:null;
		
		if(key.equals("progress")) return progress;
		if(key.equals("progresscomp")) return progress.i();
		
		if(key.equals("time_done")) return timeIndicator.r("display_done");
		if(key.equals("time_left")) return timeIndicator.r("display_left");
		if(key.equals("time_total")) return timeIndicator.r("display_total");
		if(key.equals("time_start")) return timeIndicator.r("display_start");
		if(key.equals("time_end")) return timeIndicator.r("display_end");
		
		if(key.equals("state")) return state;
		if(key.equals("task")) return task;
		if(key.equals("startdate")) return startdate;
		if(key.equals("enddate")) return enddate;
		
		if(key.equals("keys"))
			return new String[]{
				"path","path1","progress","progresscomp",
				"state","task","startdate","enddate",
				"time_done","time_left","time_total","time_start","time_end"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void run()
	{
		try
		{
			if(path==null) throw new Exception("Path not initialized yet");
			if(task==null) throw new Exception("Task not initialized yet");
		
			setStart(STATE_RUNNING);
			interrupt.clear();
			
			((P)task).p(new Object[]{new File(path),progress,interrupt});
			
			if(interrupt.isEmpty()) setEnd(STATE_COMPLETE);
			else setEnd(STATE_INTERRUPTED);
		}
		catch(Exception e)
		{
			Outside.err(this,"run()",e);
			setEnd(STATE_FAILED);
		}
	}
	
	
	
	
	
	
	private void setStart(String state)
	{
		this.startdate = new Date();
		this.state = state;
		stateModified();
	}
	
	private void setEnd(String state)
	{
		this.enddate = new Date();
		this.state = state;
		stateModified();
	}
	
	
	
	
	
	private boolean isPending()
	{return state==STATE_PENDING;}
	
	
	private void stateModified()
	{send(this,"stateModified()");}
	
}
