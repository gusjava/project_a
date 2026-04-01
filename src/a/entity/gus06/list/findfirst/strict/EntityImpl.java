package a.entity.gus06.list.findfirst.strict;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		F filter = (F) o[1];
		
		Object result = null;
		int nb = input.size();
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			if(filter.f(element))
			{
				if(result!=null) throw new Exception("Many results found in strict mode");
				result = element;
			}
		}
		if(result==null) throw new Exception("No result found in strict mode");
		return result;
	}
}
