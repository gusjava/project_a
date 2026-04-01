package a.entity.gus06.math.tabbigint.sum;

import a.framework.*;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20181226";}

	
	public Object t(Object obj) throws Exception
	{
		BigInteger[] nn = (BigInteger[]) obj;
		BigInteger sum = BigInteger.ZERO;
		for(BigInteger n:nn) sum = sum.add(n);
		return sum;
	}
}
