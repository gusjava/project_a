package a.entity.gus06.array.objectarray.max.of;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190203";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t = (T) o[1];
		
		if(input.length==0) return null;
		Comparable maxValue = (Comparable) t.t(input[0]);
		
		for(int i=1;i<input.length;i++)
		{
			Comparable value = (Comparable) t.t(input[i]);
			if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return maxValue;
	}
}
