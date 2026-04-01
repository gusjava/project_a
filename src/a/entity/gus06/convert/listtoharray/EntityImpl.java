package a.entity.gus06.convert.listtoharray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		H[] yy = new H[number];
		for(int i=0;i<number;i++) yy[i] = (H) l.get(i);
		return yy;
	}
}
