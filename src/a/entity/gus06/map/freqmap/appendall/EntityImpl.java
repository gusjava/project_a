package a.entity.gus06.map.freqmap.appendall;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160821";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object data = o[1];
		
		appendAll(map,data);
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object data = o[1];
		
		Map map1 = new HashMap(map);
		appendAll(map1,data);
		return map1;
	}
	
	
	
	
	private void appendAll(Map map, Object data) throws Exception
	{
		if(data instanceof Collection)
			appendAll(map,(Collection) data);
		else if(data instanceof Iterator)
			appendAll(map,(Iterator) data);
		else if(data instanceof Map)
			appendAll(map,(Map) data);
		else throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	private void appendAll(Map map, Collection col)
	{
		Iterator it = col.iterator();
		while(it.hasNext()) append(map,it.next());
	}
	
	private void appendAll(Map map, Iterator it)
	{
		while(it.hasNext()) append(map,it.next());
	}
	
	private void appendAll(Map map, Map m)
	{
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Number k = (Number) m.get(key);
			append(map,key,k);
		}
	}
	
	
	
	
	
	
	private void append(Map map, Object key)
	{
		if(!map.containsKey(key))
		{map.put(key,Integer.valueOf(1));return;}
		
		Number n = (Number) map.get(key);
		map.put(key,sum(n,1));
	}
	
	private void append(Map map, Object key, Number k)
	{
		if(!map.containsKey(key))
		{map.put(key,k);return;}
		
		Number n = (Number) map.get(key);
		map.put(key,sum(n,k));
	}
	
	
	private Number sum(Number n1, Number n2)
	{
		try{return Integer.valueOf(n1.intValue()+n2.intValue());}
		catch(Exception e){}
		
		return Long.valueOf(n1.longValue()+n2.longValue());
	}
}
