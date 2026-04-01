package a.entity.gus06.data.perform.sqrt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service sqrtFunction;
	
	public EntityImpl() throws Exception
	{
		sqrtFunction = Outside.service(this,"gus06.feature.op.function.sqrt");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof H) return sqrtFunction.t(obj);
		
		if(obj instanceof Integer) return Double.valueOf(Math.sqrt(toInt(obj)));
		if(obj instanceof Double) return Double.valueOf(Math.sqrt(toDouble(obj)));
		if(obj instanceof Float) return Float.valueOf((float) Math.sqrt(toFloat(obj)));
		if(obj instanceof Long) return Double.valueOf(Math.sqrt(toLong(obj)));
		
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.sqrt(n[i]);
			return n1;
		}
		if(obj instanceof double[])
		{
			double[] n = (double[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.sqrt(n[i]);
			return n1;
		}
		if(obj instanceof float[])
		{
			float[] n = (float[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.sqrt(n[i]);
			return n1;
		}
		if(obj instanceof long[])
		{
			long[] n = (long[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = Math.sqrt(n[i]);
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
