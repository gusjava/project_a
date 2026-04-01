package a.entity.gus06.list.inject;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		int nb = input.size();
		Object value = null;
		
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			value = t.t(new Object[]{element,value});
		}
		return value;
	}
}
