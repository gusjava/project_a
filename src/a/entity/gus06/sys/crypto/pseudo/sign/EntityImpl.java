package a.entity.gus06.sys.crypto.pseudo.sign;

import a.framework.*;
import java.security.PrivateKey;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141015";}


	private Service findPrivateKey;
	private Service sign;


	public EntityImpl() throws Exception
	{
		findPrivateKey = Outside.service(this,"gus06.sys.crypto.pseudo.find.privatekey");
		sign = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.sign.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		PrivateKey key = (PrivateKey) findPrivateKey.g();
		if(key==null) return null;
		return sign.t(new Object[]{key,obj});
	}
}
