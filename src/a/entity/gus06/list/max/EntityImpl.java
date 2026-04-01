package a.entity.gus06.list.max;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		
		if(input.isEmpty()) return null;
		Comparable maxValue = (Comparable) input.get(0);
		
		for(int i=1;i<input.size();i++)
		{
			Comparable value = (Comparable) input.get(i);
			if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return maxValue;
	}
}
