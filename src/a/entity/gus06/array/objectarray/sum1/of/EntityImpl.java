package a.entity.gus06.array.objectarray.sum1.of;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190521";}
	
	
	private Service findDouble;
	
	public EntityImpl() throws Exception
	{
		findDouble = Outside.service(this,"gus06.find.double1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t = (T) o[1];
		
		if(input.length==0) return null;
		double sum = 0;
		
		for(int i=0;i<input.length;i++)
		{
			Object element = t.t(input[i]);
			Double d = toDouble(element);
			if(d!=null) sum += d.doubleValue();
		}
		return Double.valueOf(sum);
	}
	
	private Double toDouble(Object obj)
	{
		try{return (Double) findDouble.t(obj);}
		catch(Exception e){return null;}
	}
}
