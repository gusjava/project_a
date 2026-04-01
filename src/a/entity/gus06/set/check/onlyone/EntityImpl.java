package a.entity.gus06.set.check.onlyone;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160805";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		F filter = (F) o[1];
		
		int found = 0;
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(!filter.f(element)) found++;
			if(found>1) return false;
		}
		return found==1;
	}
}
