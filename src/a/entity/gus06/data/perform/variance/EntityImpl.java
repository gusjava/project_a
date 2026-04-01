package a.entity.gus06.data.perform.variance;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.Icon;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}


	private Service variance_int;
	private Service variance_double;
	private Service variance_float;
	private Service variance_long;
	
	
	public EntityImpl() throws Exception
	{
		variance_int = Outside.service(this,"gus06.math.tabint.variance");
		variance_double = Outside.service(this,"gus06.math.tabdouble.variance");
		variance_float = Outside.service(this,"gus06.math.tabfloat.variance");
		variance_long = Outside.service(this,"gus06.math.tablong.variance");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[]) return variance_int.t(obj);
		if(obj instanceof double[]) return variance_double.t(obj);
		if(obj instanceof float[]) return variance_float.t(obj);
		if(obj instanceof long[]) return variance_long.t(obj);
		
		if(obj instanceof Object[]) return variance((Object[]) obj);
		if(obj instanceof List) return variance((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double variance(Object[] oo) throws Exception
	{
		double sum = 0;
		int count = oo.length;
		for(int i=0;i<count;i++) sum += toDouble(oo[i]);
		double avg = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++) sum += Math.pow(toDouble(oo[i])-avg,2);
		return Double.valueOf(sum/count);
	}
	
	private Double variance(List l) throws Exception
	{
		double sum = 0;
		int count = l.size();
		for(int i=0;i<count;i++) sum += toDouble(l.get(i));
		double avg = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++) sum += Math.pow(toDouble(l.get(i))-avg,2);
		return Double.valueOf(sum/count);
	}
	
	private double toDouble(Object obj) throws Exception
	{
		if(obj instanceof Number) return ((Number) obj).doubleValue();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
