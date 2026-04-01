package a.entity.gus06.crypto.secretkey.convert.bytearraytokey.des;

import a.framework.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141005";}
	
	
	public Object t(Object obj) throws Exception
	{return bytesToKey((byte[]) obj);}
	
	
	public SecretKey bytesToKey(byte[] input) throws Exception
	{
		SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
		KeySpec spec = new DESKeySpec(input);
		return desFactory.generateSecret(spec);
	}
}
