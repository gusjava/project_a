package a.entity.gus06.list.avg1.of;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190520";}
	
	
	
	private Service findDouble;
	
	public EntityImpl() throws Exception
	{
		findDouble = Outside.service(this,"gus06.find.double1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		if(input.isEmpty()) return null;
		
		int count = input.size();
		double sum = 0;
		int ignore = 0;
		
		for(int i=0;i<count;i++)
		{
			Object element = t.t(input.get(i));
			Double d = toDouble(element);
			if(d!=null) sum += d.doubleValue();
			else ignore++;
		}
		return Double.valueOf(sum/(count-ignore));
	}
	
	private Double toDouble(Object obj)
	{
		try{return (Double) findDouble.t(obj);}
		catch(Exception e){return null;}
	}
}
