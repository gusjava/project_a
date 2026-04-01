package a.entity.gus06.math.tabdouble.valuemap.count;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170709";}

	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		Map m = new HashMap();
		for(double d:dd)
		{
			Double d1 = Double.valueOf(d);
			if(m.containsKey(d1))
			{
				Integer n = (Integer) m.get(d1);
				m.put(d1,Integer.valueOf(n.intValue()+1));
			}
			else
			{
				m.put(d1,Integer.valueOf(1));
			}
		}
		return m;
	}
}
