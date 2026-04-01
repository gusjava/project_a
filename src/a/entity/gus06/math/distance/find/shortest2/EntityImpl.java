package a.entity.gus06.math.distance.find.shortest2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		Object target = o[1];
		T t = (T) o[2];
		
		int number = l.size();
		if(number<1) throw new Exception("Invalid element number: "+number);
		
		double distance = Double.MAX_VALUE;
		int a = -1;
		
		for(int i=0;i<number;i++)
		{
			Object aa = l.get(i);
			
			double d = ((Double) t.t(new Object[]{aa,target})).doubleValue();
			if(d < distance)
			{
				distance = d;
				a = i;
			}
		}
		
		return new Object[]{
			Integer.valueOf(a),
			Double.valueOf(distance)
		};
	}
}
