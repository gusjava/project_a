package a.entity.gus06.math.tabdouble.valuemap.proba;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170709";}


	private Service count;
	
	public EntityImpl() throws Exception
	{
		count = Outside.service(this,"gus06.math.tabdouble.valuemap.count");
	}
	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		Map countMap = (Map) count.t(dd);
		double total = (double) dd.length;
		
		Map m = new HashMap();
		Iterator it = countMap.keySet().iterator();
		while(it.hasNext())
		{
			Double d = (Double) it.next();
			Integer count = (Integer) countMap.get(d);
			double p = count.doubleValue()/total;
			m.put(d,Double.valueOf(p));
		}
		return m;
	}
}
