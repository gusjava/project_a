package a.entity.gus06.data.perform.random.list;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250709";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return random((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
	
	private Object random(List list)
	{
		if(list.isEmpty()) return null;
		return list.get(random(list.size()));
	}
}