package a.entity.gus06.data.perform.max2r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170820";}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof Number)
		{
			return max((Number) input,(Number) o[1]);
		}
		if(input instanceof int[])
		{
			return max((int[]) input,o[1]);
		}
		if(input instanceof double[])
		{
			return max((double[]) input,o[1]);
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private double perform(double v1, double v2) throws Exception
	{
		double a1 = Math.abs(v1);
		double a2 = Math.abs(v2);
		if(a1>=a2) return v1;
		return Math.signum(v1)*a2;
	}
	
	
	
	private Object max(Number n1, Number n2) throws Exception
	{
		double v1 = n1.doubleValue();
		double v2 = n2.doubleValue();
		return Double.valueOf(perform(v1,v2));
	}
	
	
	
	
	
	private Object max(int[] n1, Object n2) throws Exception
	{
		if(n2 instanceof Integer) return max(n1,(Integer) n2);
		if(n2 instanceof Double) return max(n1,(Double) n2);
		if(n2 instanceof int[]) return max(n1,(int[]) n2);
		if(n2 instanceof double[]) return max(n1,(double[]) n2);
		
		throw new Exception("Invalid data type: "+n2.getClass().getName());
	}
	
	private Object max(int[] n1, Integer n2) throws Exception
	{
		double[] output = new double[n1.length];
		int v = n2.intValue();
		for(int i=0;i<n1.length;i++) output[i] = perform((double) n1[i],(double) v);
		return output;
	}
	
	private Object max(int[] n1, Double n2) throws Exception
	{
		double[] output = new double[n1.length];
		double v = n2.doubleValue();
		for(int i=0;i<n1.length;i++) output[i] = perform((double) n1[i],v);
		return output;
	}
	
	private Object max(int[] n1, int[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = perform((double) n1[i],(double) n2[i]);
		return output;
	}
	
	private Object max(int[] n1, double[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = perform((double) n1[i],n2[i]);
		return output;
	}
	
	
	
	
	private Object max(double[] n1, Object n2) throws Exception
	{
		if(n2 instanceof Integer) return max(n1,(Integer) n2);
		if(n2 instanceof int[]) return max(n1,(int[]) n2);
		if(n2 instanceof Double) return max(n1,(Double) n2);
		if(n2 instanceof double[]) return max(n1,(double[]) n2);
		
		throw new Exception("Invalid data type: "+n2.getClass().getName());
	}
	
	private Object max(double[] n1, Integer n2) throws Exception
	{
		double[] output = new double[n1.length];
		int v = n2.intValue();
		for(int i=0;i<n1.length;i++) output[i] = perform(n1[i],(double) v);
		return output;
	}
	
	private Object max(double[] n1, Double n2) throws Exception
	{
		double[] output = new double[n1.length];
		double v = n2.doubleValue();
		for(int i=0;i<n1.length;i++) output[i] = perform(n1[i],v);
		return output;
	}
	
	private Object max(double[] n1, int[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = perform(n1[i],(double) n2[i]);
		return output;
	}
	
	private Object max(double[] n1, double[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = perform(n1[i],n2[i]);
		return output;
	}
}
