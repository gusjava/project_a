package a.entity.gus06.array.doublearray.collect;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160711";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] input = (double[]) o[0];
		double[] output = (double[]) t(obj);
		
		for(int i=0;i<input.length;i++)
		input[i] = output[i];
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		double[] input = (double[]) o[0];
		H h = (H) o[1];
		
		int nb = input.length;
		double[] output = new double[nb];
		
		for(int i=0;i<nb;i++)
		{
			double value = input[i];
			output[i] = h.h(value);
		}
		return output;
	}
}
