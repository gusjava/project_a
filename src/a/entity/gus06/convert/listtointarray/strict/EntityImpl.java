package a.entity.gus06.convert.listtointarray.strict;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		int[] yy = new int[number];
		for(int i=0;i<number;i++)
		{
			Object element = l.get(i);
			if(!(element instanceof Integer)) return null;
			yy[i] = ((Integer) element).intValue();
		}
		return yy;
	}
}
