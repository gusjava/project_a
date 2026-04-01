package a.entity.gus06.data.perform.random.weighted;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191018";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return random((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object random(Map m)
	{
		List list = new ArrayList();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			int n = toInt(m.get(key));
			for(int i=0;i<n;i++) list.add(key);
		}
		return random(list);
	}
	
	private Object random(List list)
	{
		if(list.isEmpty()) return null;
		return list.get(random(list.size()));
	}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
	
	private int toInt(Object obj)
	{return ((Integer) obj).intValue();}
}
