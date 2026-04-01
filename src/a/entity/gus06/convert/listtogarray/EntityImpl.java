package a.entity.gus06.convert.listtogarray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		G[] yy = new G[number];
		for(int i=0;i<number;i++) yy[i] = (G) l.get(i);
		return yy;
	}
}
