package a.entity.gus.x.crypto.secretkey.convert.keytobytearray.des;

import a.framework.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20141005";}
	
	public Object t(Object obj) throws Exception
	{return KeyToBytes((SecretKey) obj);}
	
	public byte[] KeyToBytes(SecretKey key) throws Exception
	{
		SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec spec = (DESKeySpec)desFactory.getKeySpec(key,DESKeySpec.class);
		return spec.getKey();
	}
}
