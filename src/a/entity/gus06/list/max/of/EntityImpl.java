package a.entity.gus06.list.max.of;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		if(input.isEmpty()) return null;
		Comparable maxValue = (Comparable) t.t(input.get(0));
		
		for(int i=1;i<input.size();i++)
		{
			Comparable value = (Comparable) t.t(input.get(i));
			if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return maxValue;
	}
}
