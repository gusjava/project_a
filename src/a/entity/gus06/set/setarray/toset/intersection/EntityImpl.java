package a.entity.gus06.set.setarray.toset.intersection;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	public Object t(Object obj) throws Exception
	{
		Set[] ss = (Set[]) obj;
		Set set = new HashSet();
		if(ss.length==0) return set;
		
		Iterator it = ss[0].iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(inEvery(ss,element)) set.add(element);
		}
		return set;
	}
	
	
	private boolean inEvery(Set[] ss, Object element)
	{
		for(Set s:ss) if(!s.contains(element)) return false;
		return true;
	}
}
