package a.entity.gus06.data.perform.sum1;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190521";}


	private Service findDouble;
	
	
	public EntityImpl() throws Exception
	{
		findDouble = Outside.service(this,"gus06.find.double1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[]) return sum((Object[]) obj);
		if(obj instanceof List) return sum((List) obj);
		if(obj instanceof Set) return sum((Set) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double sum(Object[] oo) throws Exception
	{
		double sum = 0;
		int count = oo.length;
		for(int i=0;i<count;i++)
		{
			Double d = toDouble(oo[i]);
			if(d!=null) sum += d;
		}
		return Double.valueOf(sum);
	}
	
	private Double sum(List l) throws Exception
	{
		double sum = 0;
		int count = l.size();
		for(int i=0;i<count;i++)
		{
			Double d = toDouble(l.get(i));
			if(d!=null) sum += d;
		}
		return Double.valueOf(sum);
	}
	
	private Double sum(Set s) throws Exception
	{
		return sum(new ArrayList(s));
	}
	
	private Double toDouble(Object obj)
	{
		try{return (Double) findDouble.t(obj);}
		catch(Exception e){return null;}
	}
}
