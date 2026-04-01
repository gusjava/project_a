package a.entity.gus06.map.string.seqvalue.complete.sorted;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Collections;

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
		String[] nn = oldSequence.split(";");
		Set set = new HashSet();
		for(String n : nn) set.add(n);
		set.add(value);
		
		List list = new ArrayList(set);
		Collections.sort(list);
		
		int nb = list.size();
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			if(i<nb-1) b.append(list.get(i)+";");
			else b.append(""+list.get(i));
		}
		return b.toString();
	}
}
