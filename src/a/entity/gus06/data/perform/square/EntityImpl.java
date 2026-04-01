package a.entity.gus06.data.perform.square;

import a.framework.*;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service squareFunction;
	
	public EntityImpl() throws Exception
	{
		squareFunction = Outside.service(this,"gus06.feature.op.function.square");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof H) return squareFunction.t(obj);
		
		if(obj instanceof Integer)
		{
			int n = toInt(obj);
			return Integer.valueOf(n*n);
		}
		if(obj instanceof Double)
		{
			double n = toDouble(obj);
			return Double.valueOf(n*n);
		}
		if(obj instanceof Float)
		{
			float n = toFloat(obj);
			return Float.valueOf(n*n);
		}
		if(obj instanceof Long)
		{
			long n = toLong(obj);
			return Long.valueOf(n*n);
		}
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			int[] n1 = new int[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*n[i];
			return n1;
		}
		if(obj instanceof double[])
		{
			double[] n = (double[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*n[i];
			return n1;
		}
		if(obj instanceof float[])
		{
			float[] n = (float[]) obj;
			float[] n1 = new float[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*n[i];
			return n1;
		}
		if(obj instanceof long[])
		{
			long[] n = (long[]) obj;
			long[] n1 = new long[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*n[i];
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
