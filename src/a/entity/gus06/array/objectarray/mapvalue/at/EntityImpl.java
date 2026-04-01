package a.entity.gus06.array.objectarray.mapvalue.at;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		Object value = o[1];
		
		Map output = new HashMap();
		int nb = input.length;
		
		for(int i=0;i<nb;i++)
		{
			Object key = input[i];
			output.put(key,value);
		}
		return output;
	}
}
