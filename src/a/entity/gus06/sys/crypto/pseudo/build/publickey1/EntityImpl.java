package a.entity.gus06.sys.crypto.pseudo.build.publickey1;

import a.framework.*;
import java.util.Map;
import java.security.spec.DSAPublicKeySpec;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}


	private Service verifySign;
	private Service build;


	public EntityImpl() throws Exception
	{
		verifySign = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.verify.hexa");
		build = Outside.service(this,"gus06.sys.crypto.pseudo.build.publickey");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String owner = get(map,"owner");
		String sign = get(map,"sign");
		
		PublicKey key = (PublicKey) build.t(map);
		boolean ok = verifySign(key,owner,sign);
		
		return ok?key:null;
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	private boolean verifySign(PublicKey key, String owner, String sign) throws Exception
	{return verifySign.f(new Object[]{key,owner,sign});}
}
