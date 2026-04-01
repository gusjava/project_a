package a.entity.gus06.list.findall.after1.first;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170115";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		F filter = (F) o[1];
		
		List output = new ArrayList();
		int nb = input.size();
		
		boolean found = false;
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			if(found) output.add(element);
			if(filter.f(element)) found = true;
		}
		return output;
	}
}
