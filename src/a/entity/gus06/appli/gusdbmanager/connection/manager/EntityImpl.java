package a.entity.gus06.appli.gusdbmanager.connection.manager;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, R, G, F {

	public String creationDate() {return "20150613";}

	
	public static final long RESET_DELAY = 300_000; //5 min
	//public static final long RESET_DELAY = 10_000; //10 sec
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private String now() {return sdf.format(new Date());}


	
	private Service builder;
	private Service buildDisplay;
	
	private Map holders;
	private Timer timer;
	
	private Object lastModified;
	
	
	
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.appli.gusdbmanager.connection.builder");
		buildDisplay = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors.builddisplay");
		
		holders = new HashMap();
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	




	public Object g() throws Exception
	{return holders;}

	
	
	
	public boolean f(Object obj) throws Exception
	{
		String id = (String) obj;
		if(!holders.containsKey(id))
			throw new Exception("Unknown connection id: "+id);
		return getStatus(id).equals(CX_STATUS.STATUS_CONNECTED);
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.startsWith("lastModified"))	return lastModified;
	   	
		if(key.startsWith("status_"))		return getStatus(part2(key));
		if(key.startsWith("log_"))		return getLog(part2(key));
		if(key.startsWith("exception_"))	return getException(part2(key));
		
		return getHolder(key);
	}
	
	
	
	
	
	private String part2(String s)
	{return s.split("_",2)[1];}

	
	
	
	
	
	private CxHolder getHolder(String id) throws Exception
	{
		if(!holders.containsKey(id))
			holders.put(id,new CxHolder(id));
		return (CxHolder) holders.get(id);
	}
	
	
	
	private String getStatus(String id) throws Exception
	{return (String) getHolder(id).r("status");}
	
	private String getLog(String id) throws Exception
	{return (String) getHolder(id).r("log");}
	
	private String getException(String id) throws Exception
	{return (String) getHolder(id).r("exception");}
	
	
	
	
	
	

	private void modified()
	{send(this,"modified()");}
	
	
	
	
	
	
	
	
	
	private class CxHolder extends S1 implements G, R, F, V
	{
		private String id;
		private String display;
		
		private Connection cx;
		private Exception exception;
		private String status = CX_STATUS.STATUS_DISCONNECTED;
		private StringBuffer log = new StringBuffer();
		
		private TimerTask clearTask;
		
		
		
		public CxHolder(String id) throws Exception
		{
			this.id = id;
			display = (String) buildDisplay.t(id);
		}
		
		
		public synchronized Object g() throws Exception
		{
			if(cx!=null && cx.isClosed()) cx = null;
			if(cx==null) initCx();
			
			restartClearTask();
			return cx;
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("id")) return id;
			if(key.equals("display")) return display;
			if(key.equals("cx")) return cx;
			if(key.equals("status")) return status;
			if(key.equals("exception")) return exception;
			if(key.equals("log")) return log.toString();
			
			if(key.equals("keys")) return new String[]{"id ","display","cx","status","exception","log"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public boolean f(Object obj) throws Exception
		{return status.equals(CX_STATUS.STATUS_CONNECTED);}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("clear")) {clearCx();return;}
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		private void initCx()
		{
			try
			{
				cx = null;
				exception = null;
				clearResetTask();
				
				changeStatus(CX_STATUS.STATUS_CONNECTING,"Attempt to connect");
				cx = (Connection) builder.t(id);
				changeStatus(CX_STATUS.STATUS_CONNECTED,"Successfully Connected");
			}
			catch(Exception e)
			{
				cx = null;
				exception = e;
				
				Outside.err(EntityImpl.this,"initCx()",e);
				changeStatus(CX_STATUS.STATUS_FAILED,"Connection failed ("+exceptionDesc()+")");
			}
		}
		
		
		
		
		
		
		
		
		
		
		private void clearResetTask()
		{
			if(clearTask!=null) clearTask.cancel();
			clearTask = null;
		}
		
		
		private void restartClearTask()
		{
			if(clearTask!=null) clearTask.cancel();
			clearTask = new TimerTask(){public void run(){clearCx();}};
			timer.schedule(clearTask,RESET_DELAY);
		}
		
		
		private void clearCx()
		{
			clearTask = null;
			
			if(cx==null) return;
			if(status.equals(CX_STATUS.STATUS_CONNECTING)) return;
			if(status.equals(CX_STATUS.STATUS_DISCONNECTED)) return;
			if(status.equals(CX_STATUS.STATUS_FAILED)) return;
			
			cx = null;
			changeStatus(CX_STATUS.STATUS_DISCONNECTED,"Connection clearded");
		}
		
		
		
		
		
		
		
		private void changeStatus(String status, String message)
		{
			this.status = status;
			log.append(now()+" "+message+"\n");
			
			lastModified = this;
			statusChanged();
			modified();
		}
		
		
		
		
		private String exceptionDesc()
		{
			if(exception==null) return "<not found>";
			String m = exception.getMessage();
			if(m.startsWith(">")) return m.substring(1);
			return m;
		}
		
		
		private void statusChanged()
		{send(this,"statusChanged()");}
	}
}
