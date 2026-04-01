package a.entity.gus06.data.perform.pop.last;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220620";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)		return pop((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object pop(List list)
	{
		if(list.isEmpty()) return null;
		return list.remove(list.size()-1);
	}
}