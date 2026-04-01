package a.entity.gus06.convert.listtoobjarray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		Object[] yy = new Object[number];
		for(int i=0;i<number;i++) yy[i] = l.get(i);
		return yy;
	}
}
