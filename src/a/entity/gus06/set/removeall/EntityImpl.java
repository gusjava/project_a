package a.entity.gus06.set.removeall;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151124";}
	
	
	public void p(Object obj) throws Exception
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
			if(!filter.f(element)) output.add(element);
		}
		
		input.clear();
		input.addAll(output);
	}
}
