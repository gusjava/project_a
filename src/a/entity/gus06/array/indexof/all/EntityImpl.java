package a.entity.gus06.array.indexof.all;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] array = (Object[]) o[0];
		Object element = o[1];
		
		List indexes = new ArrayList();
		for(int i=0;i<array.length;i++)
		{
			if(array[i].equals(element)) indexes.add(Integer.valueOf(i));
		}
		return indexes;
	}
}