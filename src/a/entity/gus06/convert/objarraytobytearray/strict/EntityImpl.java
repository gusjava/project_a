package a.entity.gus06.convert.objarraytobytearray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160308";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		byte[] yy = new byte[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Byte)) return null;
			yy[i] = ((Byte) oo[i]).byteValue();
		}
		return yy;
	}
}