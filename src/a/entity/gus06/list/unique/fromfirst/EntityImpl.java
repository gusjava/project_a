package a.entity.gus06.list.unique.fromfirst;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}

	
	
	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		List l1 = new ArrayList();
		
		HashSet set = new HashSet();
		for(int i=0;i<l.size();i++)
		{
			Object element = l.get(i);
			if(!set.contains(element))
			{
				l1.add(element);
				set.add(element);
			}
		}
		return l1;
	}
}
