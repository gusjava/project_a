package a.entity.gus06.array.objectarray.sum.of;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190203";}
	
	
	
	
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
			Number value = (Number) t.t(input[i]);
			sum += value.doubleValue();
		}
		return Double.valueOf(sum);
	}
}
