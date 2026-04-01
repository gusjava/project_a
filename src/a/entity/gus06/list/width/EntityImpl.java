package a.entity.gus06.list.width;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	private Service performDistance;
	
	public EntityImpl() throws Exception
	{
		performDistance = Outside.service(this,"gus06.data.perform.distance");
	}

	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		
		if(input.isEmpty()) return null;
		Comparable minValue = (Comparable) input.get(0);
		Comparable maxValue = minValue;
		
		for(int i=1;i<input.size();i++)
		{
			Comparable value = (Comparable) input.get(i);
			if(value.compareTo(minValue)<0) minValue = value;
			if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return performDistance.t(new Object[]{maxValue,minValue});
	}
}
