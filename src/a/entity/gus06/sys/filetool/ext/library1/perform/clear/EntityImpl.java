package a.entity.gus06.sys.filetool.ext.library1.perform.clear;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200311";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service confirm;

	public EntityImpl() throws Exception
	{
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String struct = get0(map,STRUCT);
		if(struct==null) return false;
		
		boolean ok = confirm.f("You are about to clear the library. Continue ?");
		if(!ok) return false;
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith(DISPLAY+".")) it.remove();
			if(key.startsWith(CONTENT+".")) it.remove();
		}
		map.remove(STRUCT);
		return true;
	}

	
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
