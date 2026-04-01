package a.entity.gus06.jdbc.connection.holder;

import a.framework.*;
import java.sql.Connection;
import java.util.TimerTask;
import java.util.Timer;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150621";}
	
	public static final long RESET_DELAY = 300000; //5 min


	private Service builder;
	private Timer timer;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.jdbc.connection.builder");
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}
	
	
	
	private class Holder extends S1 implements G, P, R
	{
		private Object data;
		private Connection cx;
		private TimerTask task;
		
		public Holder(Object data)
		{this.data = data;}
		
		
		public Object g() throws Exception
		{
			if(cx==null || cx.isClosed()) create();
			else restartTask();
			
			return cx;
		}
		
		
		
		public void p(Object obj) throws Exception
		{
			String s = (String) obj;
			
			if(s.equals("update")) update();
			else if(s.equals("reset")) reset();
			else if(s.equals("create")) create();
			
			else throw new Exception("Unknown command: "+s);
		}
		
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("cx")) return cx;
			if(key.equals("data")) return data;
			if(key.equals("keys")) return new String[]{"cx","data"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		private synchronized void restartTask()
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run(){reset();}};
			timer.schedule(task,RESET_DELAY);
		}
		
		
		
		
		
		private void reset()
		{
			cx = null;
			task = null;
			//resetted();
		}
		
		private void create() throws Exception
		{
			cx = (Connection) builder.t(data);
			restartTask();
			//created();
		}
		
		private void update()
		{
			updated();
		}
		
		
		
		
		
		
		private void resetted()
		{send(this,"resetted()");}
		
		private void created()
		{send(this,"created()");}
		
		private void updated()
		{send(this,"updated()");}
	}
}
