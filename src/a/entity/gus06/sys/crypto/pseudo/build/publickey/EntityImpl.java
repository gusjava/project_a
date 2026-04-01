package a.entity.gus06.sys.crypto.pseudo.build.publickey;

import a.framework.*;
import java.util.Map;
import java.security.spec.DSAPublicKeySpec;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141016";}


	private KeyFactory kf;


	public EntityImpl() throws Exception
	{kf = KeyFactory.getInstance("DSA");}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String g = get(map,"g");
		String p = get(map,"p");
		String q = get(map,"q");
		String y = get(map,"y");
		
		DSAPublicKeySpec keySpec = new DSAPublicKeySpec(big(y),big(p),big(q),big(g));
		return kf.generatePublic(keySpec);
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	private BigInteger big(String value)
	{return new BigInteger(value);}
}
