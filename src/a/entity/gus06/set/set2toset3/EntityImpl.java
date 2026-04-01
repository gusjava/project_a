package a.entity.gus06.set.set2toset3;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161207";}

	
	
	public Object t(Object obj) throws Exception
	{
		Set[] o = (Set[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set a1 = o[0];
		Set a2 = o[1];
		
		Set b1 = new HashSet();
		Set b12 = new HashSet();
		Set b2 = new HashSet();
		
		Iterator it = a1.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(!a2.contains(element)) b1.add(element);
			else b12.add(element);
		}
		
		it = a2.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(!a1.contains(element)) b2.add(element);
		}
		
		return new Set[]{b1,b12,b2};
	}
}
