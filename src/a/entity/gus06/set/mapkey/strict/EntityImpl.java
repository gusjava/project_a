package a.entity.gus06.set.mapkey.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		T t = (T) o[1];
		
		Map output = new HashMap();
		
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			
			Object key = t.t(element);
			Object value = element;
			
			if(output.containsKey(key))
				throw new Exception("Duplicated key found: "+key);
			output.put(key,value);
		}
		return output;
	}
}
