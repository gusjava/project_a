package a.entity.gus06.sys.script1.access.tag.derived.findall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160203";}
	
	public static final String K_PARENT = "parent";
	public static final String K_DERIVED = "derived";



	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		List found = new ArrayList();
		
		while(tag!=null)
		{
			tag = findNext(tag);
			if(tag!=null) found.add(tag);
		}
		
		return found;
	}
	
	
	
	private Map findNext(Map tag)
	{
		while(tag!=null && !has(tag,K_DERIVED))
		{tag = (Map) get(tag,K_PARENT);}
		
		if(tag==null) return null;
		return (Map) get(tag,K_DERIVED);
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
}
