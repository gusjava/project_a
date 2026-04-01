package a.entity.gus06.convert.listtofilearray.strict;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		File[] yy = new File[number];
		for(int i=0;i<number;i++)
		{
			Object element = l.get(i);
			if(!(element instanceof File)) return null;
			yy[i] = (File) element;
		}
		return yy;
	}
}
