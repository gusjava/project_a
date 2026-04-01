package a.entity.gus06.data.perform.incr;

import a.framework.*;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service incrFunction;
	
	public EntityImpl() throws Exception
	{
		incrFunction = Outside.service(this,"gus06.feature.op.function.incr");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof H) return incrFunction.t(obj);
		
		if(obj instanceof Integer) return Integer.valueOf(toInt(obj)+1);
		if(obj instanceof Double) return Double.valueOf(toDouble(obj)+1);
		if(obj instanceof Float) return Float.valueOf(toFloat(obj)+1);
		if(obj instanceof Long) return Long.valueOf(toLong(obj)+1);
		
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			int[] n1 = new int[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]+1;
			return n1;
		}
		if(obj instanceof double[])
		{
			double[] n = (double[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]+1;
			return n1;
		}
		if(obj instanceof float[])
		{
			float[] n = (float[]) obj;
			float[] n1 = new float[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]+1;
			return n1;
		}
		if(obj instanceof long[])
		{
			long[] n = (long[]) obj;
			long[] n1 = new long[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]+1;
			return n1;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private int toInt(Object obj)
	{return ((Integer) obj).intValue();}
	
	private double toDouble(Object obj)
	{return ((Double) obj).doubleValue();}
	
	private float toFloat(Object obj)
	{return ((Float) obj).floatValue();}
	
	private long toLong(Object obj)
	{return ((Long) obj).longValue();}
}
