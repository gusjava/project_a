package a.entity.gus06.data.perform.sum.motley;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(hasSet(oo)) return sumSet(oo);
		if(hasList(oo)) return sumList(oo);
		return sumString(oo);
	}
	
	
	
	
	private boolean hasSet(Object[] oo)
	{
		for(Object o:oo) if(o instanceof Set) return true;
		return false;
	}
	
	private Set sumSet(Object[] oo)
	{
		Set sum = new HashSet();
		for(Object o:oo) sum.addAll(toSet(o));
		return sum;
	}
	
	private Set toSet(Object o)
	{
		if(o instanceof Set) return (Set) o;
		if(o instanceof List) return new HashSet((List) o);
		Set l = new HashSet();
		l.add(o);
		return l;
	}
	
	
	
	
	private boolean hasList(Object[] oo)
	{
		for(Object o:oo) if(o instanceof List) return true;
		return false;
	}
	
	private List sumList(Object[] oo)
	{
		List sum = new ArrayList();
		for(Object o:oo) sum.addAll(toList(o));
		return sum;
	}
	
	private List toList(Object o)
	{
		if(o instanceof List) return (List) o;
		List l = new ArrayList();
		l.add(o);
		return l;
	}
	
	

	
	private String sumString(Object[] oo) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(Object o:oo) b.append(toString(o));
		return b.toString();
	}
	
	private String toString(Object o) throws Exception
	{
		if(o==null) return "null";
		
		String s = o.toString();
		String h = Integer.toHexString(o.hashCode());
		
		if(s.endsWith("@"+h)) throw new Exception("Object not compatible with String: "+o);
		return s;
	}
}
