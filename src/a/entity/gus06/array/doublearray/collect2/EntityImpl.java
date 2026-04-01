package a.entity.gus06.array.doublearray.collect2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180116";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] input = (double[]) o[0];
		Object[] output = (Object[]) t(obj);
		
		for(int i=0;i<input.length;i++)
		input[i] = toDouble(output[i]);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] input = (double[]) o[0];
		T t = (T) o[1];
		
		int nb = input.length;
		Object[] output = new Object[nb];
		
		for(int i=0;i<nb;i++)
		{
			Double element = Double.valueOf(input[i]);
			output[i] = t.t(new Object[]{element,input});
		}
		return output;
	}
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
}
