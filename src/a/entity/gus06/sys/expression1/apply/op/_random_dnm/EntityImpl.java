package a.entity.gus06.sys.expression1.apply.op._random_dnm;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191021";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return rand((List) obj);
		if(obj instanceof double[]) return rand((double[]) obj);
		if(obj instanceof int[]) return rand((int[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	
	
	
	private Double rand(int[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data number: "+array.length);
		double n = (double) array[0];
		double m = (double) array[1];
		return rand(n,m);
	}
	
	private Double rand(double[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data number: "+array.length);
		double n = array[0];
		double m = array[1];
		return rand(n,m);
	}
	
	private Double rand(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Wrong data number: "+list.size());
		double n = toDouble(list.get(0));
		double m = toDouble(list.get(1));
		return rand(n,m);
	}
	
	private Double rand(double n, double m)
	{return Double.valueOf(Math.random()*(m-n)+n);}
}
