package a.entity.gus06.list.findall.min;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		List output = new ArrayList();
		if(input.isEmpty()) return output;
		
		Object element0 = input.get(0);
		output.add(element0);
		Comparable minValue = (Comparable) t.t(element0);
		
		for(int i=1;i<input.size();i++)
		{
			Object element = input.get(i);
			Comparable value = (Comparable) t.t(element);
			int r = value.compareTo(minValue);
			
			if(r<0)
			{
				minValue = value;
				output.clear();
				output.add(element);
			}
			else if(r==0)
			{
				output.add(element);
			}
		}
		return output;
	}
}
