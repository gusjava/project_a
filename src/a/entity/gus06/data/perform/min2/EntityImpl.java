package a.entity.gus06.data.perform.min2;

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
			return min((Number) input,(Number) o[1]);
		}
		if(input instanceof int[])
		{
			return min((int[]) input,o[1]);
		}
		if(input instanceof double[])
		{
			return min((double[]) input,o[1]);
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	
	
	private Object min(Number n1, Number n2) throws Exception
	{
		double v1 = n1.doubleValue();
		double v2 = n2.doubleValue();
		
		return v1<=v2 ? n1 : n2;
	}
	
	
	
	
	
	private Object min(int[] n1, Object n2) throws Exception
	{
		if(n2 instanceof Integer) return min(n1,(Integer) n2);
		if(n2 instanceof Double) return min(n1,(Double) n2);
		if(n2 instanceof int[]) return min(n1,(int[]) n2);
		if(n2 instanceof double[]) return min(n1,(double[]) n2);
		
		throw new Exception("Invalid data type: "+n2.getClass().getName());
	}
	
	private Object min(int[] n1, Integer n2) throws Exception
	{
		int[] output = new int[n1.length];
		int v = n2.intValue();
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],v);
		return output;
	}
	
	private Object min(int[] n1, Double n2) throws Exception
	{
		double[] output = new double[n1.length];
		double v = n2.doubleValue();
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],v);
		return output;
	}
	
	private Object min(int[] n1, int[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		int[] output = new int[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],n2[i]);
		return output;
	}
	
	private Object min(int[] n1, double[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],n2[i]);
		return output;
	}
	
	
	
	
	private Object min(double[] n1, Object n2) throws Exception
	{
		if(n2 instanceof Integer) return min(n1,(Integer) n2);
		if(n2 instanceof int[]) return min(n1,(int[]) n2);
		if(n2 instanceof Double) return min(n1,(Double) n2);
		if(n2 instanceof double[]) return min(n1,(double[]) n2);
		
		throw new Exception("Invalid data type: "+n2.getClass().getName());
	}
	
	private Object min(double[] n1, Integer n2) throws Exception
	{
		double[] output = new double[n1.length];
		int v = n2.intValue();
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],v);
		return output;
	}
	
	private Object min(double[] n1, Double n2) throws Exception
	{
		double[] output = new double[n1.length];
		double v = n2.doubleValue();
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],v);
		return output;
	}
	
	private Object min(double[] n1, int[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],n2[i]);
		return output;
	}
	
	private Object min(double[] n1, double[] n2) throws Exception
	{
		if(n1.length!=n2.length) throw new Exception("Different array length found: "+n1.length+" & "+n2.length);
		double[] output = new double[n1.length];
		for(int i=0;i<n1.length;i++) output[i] = Math.min(n1[i],n2[i]);
		return output;
	}
}
