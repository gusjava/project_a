package a.entity.gus06.crypto.asymkeys.publickey.keyspec.dsa;

import a.framework.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.DSAPublicKeySpec;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}


	private KeyFactory kf;
	
	public EntityImpl() throws Exception
	{
		kf = KeyFactory.getInstance("DSA");
	}
	
	public Object t(Object obj) throws Exception
	{
		PublicKey key = (PublicKey) obj;
		return kf.getKeySpec(key,DSAPublicKeySpec.class);
	}
}
