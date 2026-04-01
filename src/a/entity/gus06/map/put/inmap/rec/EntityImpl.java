package a.entity.gus06.map.put.inmap.rec;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20210131";}
	
	
	private Service toList;
	
	public EntityImpl() throws Exception
	{
		toList = Outside.service(this,"gus06.find.list");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		handle(map,key,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		Map map1 = new HashMap(map);
		handle(map1,key,value);
		return map1;
	}
	
	
	
	private void handle(Map map, Object key, Object value) throws Exception
	{
		List keyList = (List) toList.t(key);
		
		Map m = map;
		int nb = keyList.size();
		
		for(int i=0;i<nb;i++)
		{
			String keyElem = (String) keyList.get(i);
			if(!m.containsKey(keyElem)) m.put(keyElem,new HashMap());
			m = (Map) m.get(keyElem);
		}
		
		m.put(value,"");
	}
}