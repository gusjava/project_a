package a.entity.gus06.convert.objarraytobigintegerarray.intlong;

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
			if(!(oo[i] instanceof BigInteger) && !(oo[i] instanceof Long) && !(oo[i] instanceof Integer)) return null;
			yy[i] = toBigInteger(oo[i]);
		}
		return yy;
	}
	
	
	private BigInteger toBigInteger(Object obj) throws Exception
	{
		if(obj instanceof BigInteger) return (BigInteger) obj; 
		if(obj instanceof Long) return BigInteger.valueOf((Long) obj); 
		if(obj instanceof Integer) return BigInteger.valueOf((Integer) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
