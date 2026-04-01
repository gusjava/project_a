package a.entity.gus06.data.list.string.filter.hasdoubloon_i;

import a.framework.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250211";}
	
	public boolean f(Object obj) throws Exception
	{
		List list = (List) obj;
		Set found = new HashSet();
		for(int i=0;i<list.size();i++)
		{
			String element = ((String) list.get(i)).toLowerCase();
			if(found.contains(element)) return true;
			found.add(element);
		}
		return false;
	}
}