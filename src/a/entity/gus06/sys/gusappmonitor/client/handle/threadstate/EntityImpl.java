package a.entity.gus06.sys.gusappmonitor.client.handle.threadstate;

import a.framework.*;
import java.lang.management.ThreadInfo;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200131";}

	public static final String EMPTY_STACK = "empty stack";
	public static final String NOT_FOUND = "not found";
	

	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		P p = (P) obj;
		p.p(result());
	}
	
	
	private String result()
	{
		StringBuffer b = new StringBuffer();
		
		int count = Thread.activeCount();
 		Thread[] threads = new Thread[count];
		Thread.enumerate(threads);
		
		List blocked = new ArrayList();
		
		for(Thread t : threads)
		{
			long id = t.getId();
			String state = t.getState().name();
			String deep = deep(t);
			String name = t.getName();
			
			b.append("DEBUG:");
			b.append("id="+id+"\t");
			b.append("state="+state+"\t");
			b.append("daemon="+t.isDaemon()+"\t");
			b.append("deep="+deep+"\t");
			b.append("name="+name);
			b.append("\n");
			
			if(state.equals("BLOCKED")) blocked.add(t);
		}
		
		if(!blocked.isEmpty())
		{
			b.append("DEBUG:BLOCKED-NB="+blocked.size()+"\n");
			for(int i=0;i<blocked.size();i++)
			{
				Thread t = (Thread) blocked.get(i);
				String name = t.getName();
				long id = t.getId();
				String lockOwner = lockOwner(t);
				String lockName = lockName(t);
				
				StackTraceElement[] ste = t.getStackTrace();
				String steG = gus06_STE(ste);
				String steF = first_STE(ste);
				
				b.append("DEBUG:-----------\n");
				b.append("DEBUG:id="+id+"\n");
				b.append("DEBUG:name="+name+"\n");
				b.append("DEBUG:lockOwner="+lockOwner+"\n");
				b.append("DEBUG:lockName="+lockName+"\n");
				b.append("DEBUG:steG="+steG+"\n");
				b.append("DEBUG:steF="+steF+"\n");
			}
		}
		
		return b.toString();
	}
	
	
	
	
	private String deep(Thread t)
	{
		StackTraceElement[] ste = t.getStackTrace();
		return ste!=null ? ""+ste.length : "";
	}
	
	private String lockOwner(Thread t)
	{
		ThreadInfo[] tInfos = ManagementFactory.getThreadMXBean().getThreadInfo(new long[]{t.getId()}, true, true);
		if(tInfos==null || tInfos.length==0) return "";
		return tInfos[0].getLockOwnerName();
	}
	
	private String lockName(Thread t)
	{
		ThreadInfo[] tInfos = ManagementFactory.getThreadMXBean().getThreadInfo(new long[]{t.getId()}, true, true);
		if(tInfos==null || tInfos.length==0) return "";
		return tInfos[0].getLockName();
	}
	
	
	
	
	private String first_STE(StackTraceElement[] ste)
	{
    		if(ste.length==0) return EMPTY_STACK;
    		return toString(ste[0]);
	}
	
	private String gus06_STE(StackTraceElement[] ste)
	{
		if(ste.length==0) return EMPTY_STACK;
		for(int n = ste.length,i=0;i<n;i++)
		if(ste[i]!=null && ste[i].getClassName().startsWith("gus06."))
			return toString(ste[i]);
		return NOT_FOUND;
	}
	
	private String toString(StackTraceElement ste)
	{
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}
}
