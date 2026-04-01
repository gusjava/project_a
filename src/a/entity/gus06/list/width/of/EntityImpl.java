package a.entity.gus06.list.width.of;

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
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		if(input.isEmpty()) return null;
		Comparable minValue = (Comparable) t.t(input.get(0));
		Comparable maxValue = minValue;
		
		for(int i=1;i<input.size();i++)
		{
			Comparable value = (Comparable) t.t(input.get(i));
			if(value.compareTo(minValue)<0) minValue = value;
			if(value.compareTo(maxValue)>0) maxValue = value;
		}
		return performDistance.t(new Object[]{maxValue,minValue});
	}
}
