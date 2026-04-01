package a.entity.gus06.set.addall.strict;

import a.framework.*;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160712";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Collection data = (Collection) o[1];
		
		check(set,data);
		
		set.addAll(data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Collection data = (Collection) o[1];
		
		check(set,data);
		
		Set set1 = new HashSet(set);
		set1.addAll(data);
		
		return set1;
	}
	
	
	
	private void check(Set set, Collection data) throws Exception
	{
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(set.contains(elem))
			throw new Exception("Element already used inside set: "+elem);
		}
	}
}
