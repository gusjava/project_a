package a.entity.gus06.sys.crypto.pseudo.verify;

import a.framework.*;
import java.security.PublicKey;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141015";}


	private Service verify;
	private Service findPublicKey;
	
	
	public EntityImpl() throws Exception
	{
		verify = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.verify.hexa");
		findPublicKey = Outside.service(this,"gus06.sys.crypto.pseudo.find.publickey");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		PublicKey key = (PublicKey) findPublicKey.g();
		if(key==null) return false;
		return verify.f(new Object[]{key,o[0],o[1]});
	}
}
