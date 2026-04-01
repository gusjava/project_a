package a.entity.gus06.array.unique0;

import a.framework.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Set over = new HashSet();
		List l1 = new ArrayList();
		
		for(int i=0;i<oo.length;i++)
		{
			Object element = oo[i];
			if(!over.contains(element))
			{
				if(!l1.contains(element)) l1.add(element);
				else
				{
					l1.remove(element);
					over.add(element);
				}
			}
		}
		
		int nb = l1.size();
		Object[] oo1 = new Object[nb];
		for(int i=0;i<nb;i++) oo1[i] = l1.get(i);
		
		return oo1;
	}
}
