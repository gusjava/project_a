package a.entity.gus06.sys.expression1.apply.op._signum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer)
		{
			return Integer.valueOf(Integer.signum((Integer) obj));
		}
		
		if(obj instanceof Long)
		{
			return Integer.valueOf(Long.signum((Long) obj));
		}
		
		if(obj instanceof Number)
		{
			double value = ((Number) obj).doubleValue();
			return Integer.valueOf(value<0 ? -1 : value>0 ? 1 : 0);
		}
		
		if(obj instanceof int[])
		{
			int[] input = (int[]) obj;
			int[] result = new int[input.length];
			
			for(int i=0;i<result.length;i++)
			result[i] = signum(input[i]);
			return result;
		}
		
		if(obj instanceof long[])
		{
			long[] input = (long[]) obj;
			int[] result = new int[input.length];
			
			for(int i=0;i<result.length;i++)
			result[i] = signum(input[i]);
			return result;
		}
		
		if(obj instanceof double[])
		{
			double[] input = (double[]) obj;
			int[] result = new int[input.length];
			
			for(int i=0;i<result.length;i++)
			result[i] = signum(input[i]);
			return result;
		}
		
		if(obj instanceof float[])
		{
			float[] input = (float[]) obj;
			int[] result = new int[input.length];
			
			for(int i=0;i<result.length;i++)
			result[i] = signum(input[i]);
			return result;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int signum(int value)
	{return value<0 ? -1 : value>0 ? 1 : 0;}
	
	private int signum(long value)
	{return value<0 ? -1 : value>0 ? 1 : 0;}
	
	private int signum(double value)
	{return value<0 ? -1 : value>0 ? 1 : 0;}
	
	private int signum(float value)
	{return value<0 ? -1 : value>0 ? 1 : 0;}
}
