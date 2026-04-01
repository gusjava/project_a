package a.entity.gus06.list.mapvalue.at;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		Object value = o[1];
		
		Map output = new HashMap();
		int nb = input.size();
		
		for(int i=0;i<nb;i++)
		{
			Object key = input.get(i);
			output.put(key,value);
		}
		return output;
	}
}
