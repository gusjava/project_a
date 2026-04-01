package a.entity.gus06.array.doublearray.findnearest;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service findDistance;
	
	public EntityImpl() throws Exception
	{
		findDistance = Outside.service(this,"gus06.data.perform.distance");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] input = (double[]) o[0];
		Object value = o[1];
		
		Object output = null;
		double dist = Double.MAX_VALUE;
		
		int nb = input.length;
		for(int i=0;i<nb;i++)
		{
			Double element = Double.valueOf(input[i]);
			Number d = (Number) findDistance.t(new Object[]{element,value});
			double dist0 = d.doubleValue();
			
			if(dist0<dist)
			{
				dist = dist0;
				output = element;
			}
		}
		return output;
	}
}
