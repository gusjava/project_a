package a.entity.gus06.thread.printinfos;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20160511";}



	public void e() throws Exception
	{printInfos(System.out);}


	public void p(Object obj) throws Exception
	{printInfos((PrintStream) obj);}

	
	
	
	private void printInfos(PrintStream p)
	{
		int threadCount = Thread.activeCount();
		Thread[] t = new Thread[threadCount];
		Thread.enumerate(t);
		
		List blockedList = blockedList(t);
		int blockedCount = blockedList.size();
		
		p.println("THREAD number: "+threadCount);
		p.println("BLOCKED number: "+blockedCount);
		
		p.println();
		p.println("BLOCKED listing:");
		
		for(int i=0;i<blockedCount;i++)
		{
			Thread b = (Thread) blockedList.get(i);
			p.println(b.getName()+" ["+b.getId()+"]\t"+lastSTE(b));
		}
		
		p.println();
		p.println("THREAD detailed information:");
		
		for(int i=0;i<threadCount;i++)
		{
			p.println("-----------");
			printInfos(p,t[i]);
		}
	}
	
	
	
	
	private void printInfos(PrintStream p, Thread t)
	{
		long id = t.getId();
		int priority = t.getPriority();
		String name = t.getName();
		String state = t.getState().name();
		String group = group(t);
		boolean isDaemon = t.isDaemon();
		int ste_deep = t.getStackTrace().length;
		
		p.println("id: "+id);
		p.println("name: "+name);
		p.println("group: "+group);
		p.println("state: "+state);
		p.println("priority: "+priority);
		p.println("isDaemon: "+isDaemon);
		p.println("STE deep: "+ste_deep);
		p.println();
		
		StackTraceElement[] ste = t.getStackTrace();
		for(int i=0;i<ste.length;i++)
		p.println(toString(ste[i]));
	}

	
	
	
	
	private List blockedList(Thread[] t)
	{
		List list = new ArrayList();
		for(int i=0;i<t.length;i++)
			if(isBlocked(t[i])) list.add(t[i]);
		return list;
	}
	
	
	
	private String group(Thread t)
	{
		ThreadGroup g = t.getThreadGroup();
		return g==null?"":g.getName();
	}
	
	
	
	private boolean isBlocked(Thread t)
	{
		if(t==null) return false;
		return t.getState()==Thread.State.BLOCKED;
	}
	
	
	
	private String toString(StackTraceElement ste)
	{return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";}
	
	
	
	private String lastSTE(Thread t)
	{
		StackTraceElement[] ste = t.getStackTrace();
		return toString(ste[0]);
	}
}
