package a.entity.gus06.convert.objarraytoimagearray.strict;

import a.framework.*;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		Image[] yy = new Image[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Image)) return null;
			yy[i] = (Image) oo[i];
		}
		return yy;
	}
}
