package a.entity.gus06.map.string.seqvalue.complete;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170617";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String key = (String) o[1];
		String value = (String) o[2];
		
		complete(map,key,value);
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String key = (String) o[1];
		String value = (String) o[2];
		
		Map map1 = new HashMap(map);
		complete(map1,key,value);
		return map1;
	}
	
	private void complete(Map map, String key, String value)
	{
		if(!map.containsKey(key))
		{map.put(key,value);return;}
		
		String value0 = (String) map.get(key);
		map.put(key,newSequence(value0,value));
	}
	
	
	private String newSequence(String oldSequence, String value)
	{
		if(q(oldSequence).contains(q(value))) return oldSequence;
		return oldSequence+";"+value;
	}
	
	private String q(String s) {return ";"+s+";";}
}
