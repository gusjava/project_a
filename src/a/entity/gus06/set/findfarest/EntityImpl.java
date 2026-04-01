package a.entity.gus06.set.findfarest;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}


	private Service findDistance;
	
	public EntityImpl() throws Exception
	{
		findDistance = Outside.service(this,"gus06.data.perform.distance");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		Object value = o[1];
		
		Object output = null;
		double dist = 0;
		
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			Number d = (Number) findDistance.t(new Object[]{element,value});
			double dist0 = d.doubleValue();
			
			if(dist0>dist)
			{
				dist = dist0;
				output = element;
			}
		}
		return output;
	}
}
