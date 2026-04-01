package a.entity.gus06.set.findall.max;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		T t = (T) o[1];
		
		Set output = new HashSet();
		if(input.isEmpty()) return output;
		
		Iterator it = input.iterator();
		Object element0 = it.next();
		output.add(element0);
		Comparable maxValue = (Comparable) t.t(element0);
		
		while(it.hasNext())
		{
			Object element = it.next();
			Comparable value = (Comparable) t.t(element);
			int r = value.compareTo(maxValue);
			
			if(r>0)
			{
				maxValue = value;
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
