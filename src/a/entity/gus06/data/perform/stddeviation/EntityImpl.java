package a.entity.gus06.data.perform.stddeviation;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.Icon;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}


	private Service stddev_int;
	private Service stddev_double;
	private Service stddev_float;
	private Service stddev_long;
	
	
	public EntityImpl() throws Exception
	{
		stddev_int = Outside.service(this,"gus06.math.tabint.stddev");
		stddev_double = Outside.service(this,"gus06.math.tabdouble.stddev");
		stddev_float = Outside.service(this,"gus06.math.tabfloat.stddev");
		stddev_long = Outside.service(this,"gus06.math.tablong.stddev");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[]) return stddev_int.t(obj);
		if(obj instanceof double[]) return stddev_double.t(obj);
		if(obj instanceof float[]) return stddev_float.t(obj);
		if(obj instanceof long[]) return stddev_long.t(obj);
		
		if(obj instanceof Object[]) return stddeviation((Object[]) obj);
		if(obj instanceof List) return stddeviation((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double stddeviation(Object[] oo) throws Exception
	{
		double sum = 0;
		int count = oo.length;
		for(int i=0;i<count;i++) sum += toDouble(oo[i]);
		double avg = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++) sum += Math.pow(toDouble(oo[i])-avg,2);
		double variance = sum/count;
		double stddev = Math.sqrt(variance);
		
		return Double.valueOf(stddev);
	}
	
	private Double stddeviation(List l) throws Exception
	{
		double sum = 0;
		int count = l.size();
		for(int i=0;i<count;i++) sum += toDouble(l.get(i));
		double avg = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++) sum += Math.pow(toDouble(l.get(i))-avg,2);
		double variance = sum/count;
		double stddev = Math.sqrt(variance);
		
		return Double.valueOf(stddev);
	}
	
	private double toDouble(Object obj) throws Exception
	{
		if(obj instanceof Number) return ((Number) obj).doubleValue();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
