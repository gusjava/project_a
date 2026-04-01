package a.entity.gus06.convert.listtostringarray.strict;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		String[] yy = new String[number];
		for(int i=0;i<number;i++)
		{
			Object element = l.get(i);
			if(!(element instanceof String)) return null;
			yy[i] = (String) element;
		}
		return yy;
	}
}
