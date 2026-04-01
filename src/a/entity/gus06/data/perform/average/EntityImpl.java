package a.entity.gus06.data.perform.average;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}


	private Service avg_int;
	private Service avg_double;
	private Service avg_float;
	private Service avg_long;
	
	
	public EntityImpl() throws Exception
	{
		avg_int = Outside.service(this,"gus06.math.tabint.mean");
		avg_double = Outside.service(this,"gus06.math.tabdouble.mean");
		avg_float = Outside.service(this,"gus06.math.tabfloat.mean");
		avg_long = Outside.service(this,"gus06.math.tablong.mean");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[]) return avg_int.t(obj);
		if(obj instanceof double[]) return avg_double.t(obj);
		if(obj instanceof float[]) return avg_float.t(obj);
		if(obj instanceof long[]) return avg_long.t(obj);
		
		if(obj instanceof Object[]) return average((Object[]) obj);
		if(obj instanceof List) return average((List) obj);
		if(obj instanceof Set) return average((Set) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double average(Object[] oo) throws Exception
	{
		double sum = 0;
		int count = oo.length;
		for(int i=0;i<count;i++) sum += toDouble(oo[i]);
		return Double.valueOf(sum/count);
	}
	
	private Double average(List l) throws Exception
	{
		double sum = 0;
		int count = l.size();
		for(int i=0;i<count;i++) sum += toDouble(l.get(i));
		return Double.valueOf(sum/count);
	}
	
	private Double average(Set s) throws Exception
	{
		return average(new ArrayList(s));
	}
	
	private double toDouble(Object obj) throws Exception
	{
		if(obj instanceof Number) return ((Number) obj).doubleValue();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
