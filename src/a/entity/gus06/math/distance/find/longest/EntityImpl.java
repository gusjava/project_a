package a.entity.gus06.math.distance.find.longest;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151230";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		int number = l.size();
		if(number<2) throw new Exception("Invalid element number: "+number);
		
		double distance = 0;
		int a = -1;
		int b = -1;
		
		for(int i=0;i<number-1;i++)
		for(int j=i+1;j<number;j++)
		{
			Object aa = l.get(i);
			Object bb = l.get(j);
			
			double d = ((Double) t.t(new Object[]{aa,bb})).doubleValue();
			if(d > distance)
			{
				distance = d;
				a = i;
				b = j;
			}
		}
		
		return new Object[]{
			Integer.valueOf(a),
			Integer.valueOf(b),
			Double.valueOf(distance)
		};
	}
}
