package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.set;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
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
}
