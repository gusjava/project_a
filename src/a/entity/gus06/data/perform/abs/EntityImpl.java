package a.entity.gus06.data.perform.abs;

import a.framework.*;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}


	private Service absFunction;
	private Service absSet;
	private Service absList;
	
	public EntityImpl() throws Exception
	{
		absFunction = Outside.service(this,"gus06.feature.op.function.abs");
		absSet = Outside.service(this,"gus06.sys.opposite1.set.abs");
		absList = Outside.service(this,"gus06.sys.opposite1.list.abs");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Set) return absSet.t(obj);
		if(obj instanceof List) return absList.t(obj);
		if(obj instanceof H) return absFunction.t(obj);
		
		if(obj instanceof Integer) return Integer.valueOf(Math.abs(toInt(obj)));
		if(obj instanceof Double) return Double.valueOf(Math.abs(toDouble(obj)));
		if(obj instanceof Float) return Float.valueOf(Math.abs(toFloat(obj)));
		if(obj instanceof Long) return Long.valueOf(Math.abs(toLong(obj)));
		
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			int[] n1 = new int[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.abs(n[i]);
			return n1;
		}
		if(obj instanceof double[])
		{
			double[] n = (double[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.abs(n[i]);
			return n1;
		}
		if(obj instanceof float[])
		{
			float[] n = (float[]) obj;
			float[] n1 = new float[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.abs(n[i]);
			return n1;
		}
		if(obj instanceof long[])
		{
			long[] n = (long[]) obj;
			long[] n1 = new long[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.abs(n[i]);
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
