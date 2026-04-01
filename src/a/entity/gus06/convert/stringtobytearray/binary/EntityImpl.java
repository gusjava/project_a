package a.entity.gus06.convert.stringtobytearray.binary;

import a.framework.*;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String) obj);
	}

	private byte[] convert(String binary) throws Exception
	{
		BigInteger b = new BigInteger(binary,2);
		return b.toByteArray();
	}
}
