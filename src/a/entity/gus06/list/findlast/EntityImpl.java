package a.entity.gus06.list.findlast;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151114";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		F filter = (F) o[1];
		
		int nb = input.size();
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(nb-i-1);
			if(filter.f(element)) return element;
		}
		return null;
	}
}
