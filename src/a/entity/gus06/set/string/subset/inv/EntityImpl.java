package a.entity.gus06.set.string.subset.inv;

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
		String offset = (String) o[1];
		
		Set set1 = new HashSet();
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			set1.add(offset+value);
		}
		return set1;
	}
}
