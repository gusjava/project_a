package a.entity.gus06.array.objectarray.reverse;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160626";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] input = (Object[]) obj;
		Object[] output = (Object[]) t(obj);
		
		for(int i=0;i<input.length;i++) 
		input[i] = output[i];
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] input = (Object[]) obj;
		
		int nb = input.length;
		Object[] output = new Object[nb];
		for(int i=0;i<nb;i++) output[i] = input[nb-1-i];
		
		return output;
	}
}
