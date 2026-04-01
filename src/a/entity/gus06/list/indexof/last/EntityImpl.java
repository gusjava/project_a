package a.entity.gus06.list.indexof.last;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object element = o[1];
		
		Integer lastIndex = null;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).equals(element)) lastIndex = Integer.valueOf(i);
		}
		return lastIndex;
	}
}
