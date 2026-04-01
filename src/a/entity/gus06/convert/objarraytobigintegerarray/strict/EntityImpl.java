package a.entity.gus06.convert.objarraytobigintegerarray.strict;

import a.framework.*;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20181226";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		BigInteger[] yy = new BigInteger[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof BigInteger)) return null;
			yy[i] = (BigInteger) oo[i];
		}
		return yy;
	}
}
