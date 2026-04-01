package a.entity.gus06.array.indexof.first;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] array = (Object[]) o[0];
		Object element = o[1];
		
		for(int i=0;i<array.length;i++)
		{
			if(array[i].equals(element)) return Integer.valueOf(i);
		}
		return null;
	}
}