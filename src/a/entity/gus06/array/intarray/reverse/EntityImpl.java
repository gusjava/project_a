package a.entity.gus06.array.intarray.reverse;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160626";}
	
	
	public void p(Object obj) throws Exception
	{
		int[] input = (int[]) obj;
		int[] output = (int[]) t(obj);
		
		for(int i=0;i<input.length;i++) 
		input[i] = output[i];
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] input = (int[]) obj;
		
		int nb = input.length;
		int[] output = new int[nb];
		for(int i=0;i<nb;i++) output[i] = input[nb-1-i];
		
		return output;
	}
}
