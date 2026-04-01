package a.entity.gus06.list.sum.of;

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
		double sum = 0;
		
		for(int i=0;i<input.size();i++)
		{
			Number value = (Number) t.t(input.get(i));
			sum += value.doubleValue();
		}
		return Double.valueOf(sum);
	}
}
