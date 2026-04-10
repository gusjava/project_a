package a.entity.gus.x.threads.build.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260410";}

	public Object g() throws Exception
	{
		Map result = new HashMap();
		Map all = Thread.getAllStackTraces();
		Iterator it = all.keySet().iterator();
		while (it.hasNext())
		{
			Thread t = (Thread) it.next();
			Map info = new HashMap();
			info.put("name", t.getName());
			info.put("state", t.getState().name());
			info.put("priority", String.valueOf(t.getPriority()));
			info.put("daemon", String.valueOf(t.isDaemon()));
			result.put(String.valueOf(t.getId()), info);
		}
		return result;
	}
}
