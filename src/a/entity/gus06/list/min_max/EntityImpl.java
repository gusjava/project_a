package a.entity.gus06.list.min_max;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170427";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		
		if(input.isEmpty()) return null;
		
		Comparable minValue = (Comparable) input.get(0);
		Comparable maxValue = (Comparable) input.get(0);
		
		for(int i=1;i<input.size();i++)
		{
			Comparable value = (Comparable) input.get(i);
			if(value.compareTo(minValue)<0) minValue = value;
			else if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return new Object[]{minValue,maxValue};
	}
}
