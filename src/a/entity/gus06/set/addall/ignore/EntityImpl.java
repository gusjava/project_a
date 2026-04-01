package a.entity.gus06.set.addall.ignore;

import a.framework.*;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160808";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Collection data = (Collection) o[1];
		
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(!set.contains(elem)) set.add(elem);
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Collection data = (Collection) o[1];
		
		Set set1 = new HashSet(set);
		
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(!set1.contains(elem)) set1.add(elem);
		}
		
		return set1;
	}
}
