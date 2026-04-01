package a.entity.gus06.array.intarray.findall2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160808";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[] input = (int[]) o[0];
		F filter = (F) o[1];
		
		int nb = input.length;
		List output = new ArrayList();
		
		for(int i=0;i<nb;i++)
		{
			Integer element = Integer.valueOf(input[i]);
			if(filter.f(new Object[]{element,input})) output.add(element);
		}
		return output;
	}
}
