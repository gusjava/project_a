package a.entity.gus06.data.set.string.filter.hasdoubloon_i;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250211";}
	
	public boolean f(Object obj) throws Exception
	{
		Set set = (Set) obj;
		Set found = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String element = ((String) it.next()).toLowerCase();
			if(found.contains(element)) return true;
			found.add(element);
		}
		return false;
	}
}