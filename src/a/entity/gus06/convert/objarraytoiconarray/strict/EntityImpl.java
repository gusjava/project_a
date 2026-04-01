package a.entity.gus06.convert.objarraytoiconarray.strict;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160209";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		Icon[] yy = new Icon[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Icon)) return null;
			yy[i] = (Icon) oo[i];
		}
		return yy;
	}
}
