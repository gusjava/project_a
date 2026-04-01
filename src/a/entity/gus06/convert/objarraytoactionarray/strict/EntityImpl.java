package a.entity.gus06.convert.objarraytoactionarray.strict;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		Action[] yy = new Action[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Action)) return null;
			yy[i] = (Action) oo[i];
		}
		return yy;
	}
}
