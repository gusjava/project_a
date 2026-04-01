package a.entity.gus06.thread.watcher.builder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Watcher((Thread) obj);
	}
	
	
	
	private class Watcher extends Thread implements G
	{
		private Thread th;
		private Map map;
		
		
		public Watcher(Thread th)
		{
			super("WATCHER_"+th.getName());
			setPriority(Thread.MAX_PRIORITY);
			map = new HashMap();
			
			this.th = th;
			start();
		}
		
		public void run()
		{
			while(true)
			{
				String s = gus06_STE(th.getStackTrace());
				if(s!=null) addToMap(s);
			}
		}
		
		
		public synchronized Object g() throws Exception
		{return map;}
		
		
		
		private synchronized void addToMap(String s)
		{
			if(!map.containsKey(s)) map.put(s,Integer.valueOf(1));
			else
			{
				Integer n = (Integer) map.get(s);
				map.put(s,Integer.valueOf(n.intValue()+1));
			}
		}
		
		
		
		private String gus06_STE(StackTraceElement[] ste)
		{
			if(ste.length==0) return null;
			for(int n = ste.length,i=0;i<n;i++)
			if(ste[i]!=null && ste[i].getClassName().startsWith("gus06."))
			return toString(ste[i]);
			return null;
		}
		
		private String toString(StackTraceElement ste)
		{
			if(ste==null) return "null";
			return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
		}
	}
}
