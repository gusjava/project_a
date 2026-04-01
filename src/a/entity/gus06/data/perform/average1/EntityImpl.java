package a.entity.gus06.data.perform.average1;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190520";}


	private Service findDouble;
	
	
	public EntityImpl() throws Exception
	{
		findDouble = Outside.service(this,"gus06.find.double1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[]) return average((Object[]) obj);
		if(obj instanceof List) return average((List) obj);
		if(obj instanceof Set) return average((Set) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double average(Object[] oo) throws Exception
	{
		double sum = 0;
		int count = oo.length;
		int ignore = 0;
		for(int i=0;i<count;i++)
		{
			Double d = toDouble(oo[i]);
			if(d!=null) sum += d;
			else ignore++;
		}
		return Double.valueOf(sum/(count-ignore));
	}
	
	private Double average(List l) throws Exception
	{
		double sum = 0;
		int count = l.size();
		int ignore = 0;
		for(int i=0;i<count;i++)
		{
			Double d = toDouble(l.get(i));
			if(d!=null) sum += d;
			else ignore++;
		}
		return Double.valueOf(sum/(count-ignore));
	}
	
	private Double average(Set s) throws Exception
	{
		return average(new ArrayList(s));
	}
	
	private Double toDouble(Object obj)
	{
		try{return (Double) findDouble.t(obj);}
		catch(Exception e){return null;}
	}
}
