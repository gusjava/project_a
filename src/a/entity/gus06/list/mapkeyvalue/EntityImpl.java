package a.entity.gus06.list.mapkeyvalue;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180407";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t1 = (T) o[1];
		T t2 = (T) o[2];
		
		Map output = new HashMap();
		int nb = input.size();
		
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			
			Object key = t1.t(element);
			Object value = t2.t(element);;
			
			output.put(key,value);
		}
		return output;
	}
}
