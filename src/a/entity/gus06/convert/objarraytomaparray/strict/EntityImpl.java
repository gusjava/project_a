package a.entity.gus06.convert.objarraytomaparray.strict;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		Map[] yy = new Map[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Map)) return null;
			yy[i] = (Map) oo[i];
		}
		return yy;
	}
}
