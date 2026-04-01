package a.entity.gus06.find.set.i;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Collection) return toSet((Collection) obj);
		if(obj instanceof Object[]) return toSet((Object[]) obj);
		if(obj instanceof String) return toSet((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Set toSet(String s)
	{
		Set set = new HashSet();
		set.add(lower(s));
		return set;
	}
	
	
	private Set toSet(Object[] oo)
	{
		Set set = new HashSet();
		for(Object o:oo) set.add(lower(""+o));
		return set;
	}
	
	private Set toSet(Collection c)
	{
		Set set = new HashSet();
		for(Object o:c) set.add(lower(""+o));
		return set;
	}
	
	private String lower(String s)
	{return s.toLowerCase();}
}
