package a.entity.gus.x.thread.build.info;

import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, G, T {
	public String creationDate() {return "20260419";}

	public Object g() throws Exception
	{
		Thread t = threadSTE();
		return t!=null ? threadToMap(t) : null;
	}
	
	public Object t(Object obj) throws Exception
	{
		Thread t = threadById(obj);
		return t!=null ? threadToMap(t) : null;
	}
	
	
	
	private Thread threadSTE()
	{
		for (Thread t : Thread.getAllStackTraces().keySet())
		if (t.getName().startsWith("AWT-EventQueue")) return t;
		return null;
	}
	
	private Thread threadById(Object id)
	{
		long idVal = Long.parseLong(""+id);
		for (Thread t : Thread.getAllStackTraces().keySet())
		if (t.getId()==idVal) return t;
		return null;
	}
	
	private Map threadToMap(Thread t)
	{
		Map info = new HashMap();
		info.put("name", t.getName());
		info.put("state", t.getState().name());
		info.put("priority", String.valueOf(t.getPriority()));
		info.put("daemon", String.valueOf(t.isDaemon()));
		info.put("id", String.valueOf(t.getId()));
		info.put("ste", steToList(t));
		return info;
	}
	
	private List steToList(Thread t)
	{
		StackTraceElement[] st = t.getStackTrace();
		List list = new ArrayList();
		for (StackTraceElement el : st) list.add(el.toString());
		return list;
	}
}
