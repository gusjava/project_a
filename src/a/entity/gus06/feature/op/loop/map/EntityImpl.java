package a.entity.gus06.feature.op.loop.map;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		int n = toInt(o[1]);
		
		Map output = new HashMap(map);
		
		for(int i=0;i<n;i++)
		{
			Map output1 = new HashMap();
			Iterator it = output.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = output.get(key);
				
				if(output.containsKey(value))
				 output1.put(value,output.get(value));
			}
			output = output1;
		}
		
		return output;
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
