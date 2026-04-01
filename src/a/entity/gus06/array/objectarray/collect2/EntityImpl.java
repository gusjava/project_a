package a.entity.gus06.array.objectarray.collect2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170326";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		Object[] output = (Object[]) t(obj);
		
		for(int i=0;i<input.length;i++)
		input[i] = output[i];
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t = (T) o[1];
		
		int nb = input.length;
		Object[] output = new Object[nb];
		
		for(int i=0;i<nb;i++)
		{
			Object element = input[i];
			output[i] = t.t(new Object[]{element,input});
		}
		return output;
	}
}
