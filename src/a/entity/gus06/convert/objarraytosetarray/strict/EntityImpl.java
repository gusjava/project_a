package a.entity.gus06.convert.objarraytosetarray.strict;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		Set[] yy = new Set[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Set)) return null;
			yy[i] = (Set) oo[i];
		}
		return yy;
	}
}
