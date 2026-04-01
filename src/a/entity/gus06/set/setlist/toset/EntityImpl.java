package a.entity.gus06.set.setlist.toset;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}


	public Object t(Object obj) throws Exception
	{
		List ll = (List) obj;
		Set set = new HashSet();
		for(Object l:ll) set.addAll(toSet(l));
		return set;
	}
	
	private Set toSet(Object obj) throws Exception
	{
		if(obj instanceof Set) return (Set) obj;
		if(obj instanceof Map) return ((Map) obj).keySet();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
