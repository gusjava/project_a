package a.entity.gus06.list.multiple0;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		
		HashSet a1 = new HashSet();
		HashSet a2 = new HashSet();
		
		int nb = l.size();
		for(int i=0;i<nb;i++)
		{
			Object element = l.get(i);
			if(!a1.contains(element)) a1.add(element);
			else if(!a2.contains(element)) a2.add(element);
		}
		
		List l1 = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			Object element = l.get(i);
			if(a2.contains(element) && !l1.contains(element)) l1.add(element);
		}
		return l1;
	}
}
