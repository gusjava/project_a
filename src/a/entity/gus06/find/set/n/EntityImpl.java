package a.entity.gus06.find.set.n;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}
	
	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Collection) return toSet((Collection) obj);
		if(obj instanceof Object[]) return toSet((Object[]) obj);
		if(obj instanceof String) return toSet((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Set toSet(String s) throws Exception
	{
		Set set = new HashSet();
		set.add(normalize(s));
		return set;
	}
	
	
	private Set toSet(Object[] oo) throws Exception
	{
		Set set = new HashSet();
		for(Object o:oo) set.add(normalize(""+o));
		return set;
	}
	
	private Set toSet(Collection c) throws Exception
	{
		Set set = new HashSet();
		for(Object o:c) set.add(normalize(""+o));
		return set;
	}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
}