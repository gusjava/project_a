package a.entity.gus06.set.findone.strict;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		F filter = (F) o[1];
		
		Object result = null;
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(filter.f(element))
			{
				if(result!=null) throw new Exception("Many results found in strict mode");
				result = element;
			}
		}
		if(result==null) throw new Exception("No result found in strict mode");
		return result;
	}
}
