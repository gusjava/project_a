package a.entity.gus06.array.intarray.findlast;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200326";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[] input = (int[]) o[0];
		F filter = (F) o[1];
		
		int nb = input.length;
		for(int i=0;i<nb;i++)
		{
			Integer element = Integer.valueOf(input[nb-i-1]);
			if(filter.f(element)) return element;
		}
		return null;
	}
}
