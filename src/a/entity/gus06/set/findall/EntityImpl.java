package a.entity.gus06.set.findall;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151113";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		F filter = (F) o[1];
		
		Set output = new HashSet();
		
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(filter.f(element)) output.add(element);
		}
		return output;
	}
}
