package a.entity.gus06.list.filter.indexes;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		F filter = (F) o[1];
		
		int nb = input.size();
		List indexes = new ArrayList();
		
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			if(filter.f(element)) indexes.add(Integer.valueOf(i));
		}
		return indexes;
	}
}
