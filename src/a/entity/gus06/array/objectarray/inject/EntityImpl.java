package a.entity.gus06.array.objectarray.inject;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t = (T) o[1];
		
		int nb = input.length;
		Object value = null;
		
		for(int i=0;i<nb;i++)
		{
			Object element = input[i];
			value = t.t(new Object[]{element,value});
		}
		return value;
	}
}
