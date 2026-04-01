package a.entity.gus06.sys.script1.manager;

import a.framework.*;
import java.util.Map;
import java.util.Vector;
import java.util.Date;

public class EntityImpl extends S1 implements Entity, T, G, R {

	public String creationDate() {return "20180117";}

	private Service stopThread;
	private Service executeAfter;
	
	private Vector list;
	private Object lastWatcher;


	public EntityImpl() throws Exception
	{
		stopThread = Outside.service(this,"gus06.thread.stop");
		executeAfter = Outside.service(this,"gus06.thread.executeafter");
		list = new Vector();
	}
	
	public Object g() throws Exception
	{return lastWatcher;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list")) return list;
		if(key.equals("keys")) return new String[]{"list"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object src = o[0];
		Map context = (Map) o[1];
		
		return new Watcher(src,context);
	}
	
	
	
	private class Watcher implements E, R, P
	{
		private Object src;
		private Map context;
		private Thread thread;
		private Date startDate;
		private Date endDate;
		private boolean over;
		
		public Watcher(Object src, Map context) throws Exception
		{
			this.src = src;
			this.context = context;
			
			thread = Thread.currentThread();
			startDate = new Date();
			over = false;
			list.add(this);
			lastWatcher = this;
			
			executeAfter.p(new Object[]{thread,this});
			scriptStarted();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("src")) return src;
			if(key.equals("context")) return context;
			if(key.equals("thread")) return thread;
			if(key.equals("startDate")) return startDate;
			if(key.equals("endDate")) return endDate;
			
			if(key.equals("keys")) return new String[]{"src","context","thread","startDate","endDate"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void e() throws Exception
		{
			if(over) return;
			list.remove(this);
			
			endDate = new Date();
			lastWatcher = this;
			over = true;
			scriptEnded();
		}
		
		public void p(Object obj) throws Exception
		{
			String s = (String) obj;
			if(s.equals("stop")) {stop();return;}
			
			throw new Exception("Invalid command: "+s);
		}
		
		private void stop() throws Exception
		{
			stopThread.p(thread);
			e();
		}
	}
	
	
	
	private void scriptStarted()
	{send(this,"scriptStarted()");}
	
	
	private void scriptEnded()
	{send(this,"scriptEnded()");}
}
