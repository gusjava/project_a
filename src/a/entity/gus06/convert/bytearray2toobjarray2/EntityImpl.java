package a.entity.gus06.convert.bytearray2toobjarray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		byte[][] oo = (byte[][]) obj;
		int nb1 = oo.length;
		int nb2 = nb1>0 ? oo[0].length : 0;
		
		Byte[][] yy = new Byte[nb1][nb2];
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		yy[i][j] = Byte.valueOf(oo[i][j]);
		
		return yy;
	}
}
