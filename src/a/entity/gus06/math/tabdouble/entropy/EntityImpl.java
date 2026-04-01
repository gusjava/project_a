package a.entity.gus06.math.tabdouble.entropy;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170709";}


	private Service proba;
	
	public EntityImpl() throws Exception
	{
		proba = Outside.service(this,"gus06.math.tabdouble.valuemap.proba");
	}
	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		Map probaMap = (Map) proba.t(dd);
		
		double v = 0;
		Iterator it = probaMap.values().iterator();
		while(it.hasNext())
		{
			double p = ((Double) it.next()).doubleValue();
			v += p * Math.log(p);
		}
		return Double.valueOf(-1*v);
	}
}
