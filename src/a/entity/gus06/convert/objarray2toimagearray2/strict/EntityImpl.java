package a.entity.gus06.convert.objarray2toimagearray2.strict;

import a.framework.*;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180509";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[][] oo = (Object[][]) obj;
		int nb1 = oo.length;
		int nb2 = nb1>0 ? oo[0].length : 0;
		
		Image[][] yy = new Image[nb1][nb2];
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			if(!(oo[i][j] instanceof Image)) return null;
			yy[i][j] = (Image) oo[i][j];
		}
		return yy;
	}
}
