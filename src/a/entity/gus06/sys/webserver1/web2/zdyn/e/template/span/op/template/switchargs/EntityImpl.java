package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.template.switchargs;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}

	
	
	public Object t(Object obj) throws Exception
	{return switchArgs((Map) obj);}
	
	
	
	private Map switchArgs(Map args)
	{
		Map m = new HashMap();
		Iterator it = args.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) args.get(key);
			m.put(switchKey(key),value);
		}
		return m;
	}
	
	private String switchKey(String key)
	{
		int index = toIndex(key);
		if(index>0) return key;
		return ""+(index-1);
	}
	
	private int toIndex(String key)
	{
		try{return Integer.parseInt(key);}
		catch(NumberFormatException e)
		{return 1;}
	}
}
