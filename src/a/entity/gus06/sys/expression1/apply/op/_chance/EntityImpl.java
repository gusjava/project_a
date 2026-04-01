package a.entity.gus06.sys.expression1.apply.op._chance;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return Boolean.valueOf(chance((Integer) obj));
		if(obj instanceof Double) return Boolean.valueOf(chance((Double) obj));
		if(obj instanceof int[]) return Boolean.valueOf(chance((int[]) obj));
		if(obj instanceof double[]) return Boolean.valueOf(chance((double[]) obj));
		if(obj instanceof List) return Boolean.valueOf(chance((List) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean chance(Integer n) throws Exception
	{
		int v = n.intValue();
		if(v<0) throw new Exception("Invalid integer value: "+v);
		if(v==0) return false;
		return randomInt(v)==0;
	}
	
	private boolean chance(Double d) throws Exception
	{
		double v = d.doubleValue();
		if(v<0 || v>1) throw new Exception("Invalid double value: "+v);
		if(v==0) return false;
		if(v==1) return true;
		return Math.random()<v;
	}
	
	
	private boolean chance(int[] arr) throws Exception
	{
		if(arr.length!=2) throw new Exception("Wrong data number: "+arr.length);
		double d1 = (double) arr[0];
		double d2 = (double) arr[1];
		return chance(d1,d2);
	}
	
	private boolean chance(double[] arr) throws Exception
	{
		if(arr.length!=2) throw new Exception("Wrong data number: "+arr.length);
		double d1 = arr[0];
		double d2 = arr[1];
		return chance(d1,d2);
	}
	
	private boolean chance(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Wrong data number: "+list.size());
		double d1 = toDouble(list.get(0));
		double d2 = toDouble(list.get(1));
		return chance(d1,d2);
	}
	
	private boolean chance(double d1, double d2)
	{
		double r = randomDouble(d1+d2);
		return r<d1;
	}
	
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	
	private int randomInt(int n)
	{return (int) (Math.random()*n);}
	
	private double randomDouble(double n)
	{return Math.random()*n;}
}
