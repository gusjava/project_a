package a.entity.gus06.array.longarray.reverse;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160626";}
	
	
	public void p(Object obj) throws Exception
	{
		long[] input = (long[]) obj;
		long[] output = (long[]) t(obj);
		
		for(int i=0;i<input.length;i++) 
		input[i] = output[i];
	}
	
	
	public Object t(Object obj) throws Exception
	{
		long[] input = (long[]) obj;
		
		int nb = input.length;
		long[] output = new long[nb];
		for(int i=0;i<nb;i++) output[i] = input[nb-1-i];
		
		return output;
	}
}
