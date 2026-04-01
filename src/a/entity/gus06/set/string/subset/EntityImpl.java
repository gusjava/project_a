package a.entity.gus06.set.string.subset;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160519";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		String start = (String) o[1];
		
		int length = start.length();
		
		Set set1 = new HashSet();
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			if(value.startsWith(start))
			set1.add(value.substring(length));
		}
		return set1;
	}
}
