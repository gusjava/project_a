package a.entity.gus06.array.d2.objectarray.buildfromd1.findn2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180107";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		int nb2 = findNb2(array);
		return Integer.valueOf(nb2);
	}
	
	
	private int findNb2(Object[] array) throws Exception
	{
		int nb = 0;
		for(Object element : array)
		{
			int n = findNb(element);
			if(n>nb) nb = n;
		}
		return nb;
	}
	
	
	private int findNb(Object element) throws Exception
	{
		if(element instanceof List) return ((List) element).size();
		if(element instanceof Object[]) return ((Object[]) element).length;
		if(element instanceof int[]) return ((int[]) element).length;
		if(element instanceof double[]) return ((double[]) element).length;
		if(element instanceof float[]) return ((float[]) element).length;
		if(element instanceof long[]) return ((long[]) element).length;
		if(element instanceof boolean[]) return ((boolean[]) element).length;
		
		throw new Exception("Invalid row data type: "+element.getClass().getName());
	}
}