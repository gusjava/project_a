package a.entity.gus06.array.objectarray.mapkeyvalue;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180407";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t1 = (T) o[1];
		T t2 = (T) o[2];
		
		Map output = new HashMap();
		int nb = input.length;
		
		for(int i=0;i<nb;i++)
		{
			Object element = input[i];
			
			Object key = t1.t(element);
			Object value = t2.t(element);;
			
			output.put(key,value);
		}
		return output;
	}
}
