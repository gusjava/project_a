package a.entity.gus06.sys.crypto.pseudo.build.privatekey;

import a.framework.*;
import java.util.Map;
import java.security.spec.DSAPrivateKeySpec;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141015";}


	private KeyFactory kf;


	public EntityImpl() throws Exception
	{kf = KeyFactory.getInstance("DSA");}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String g = get(map,"g");
		String p = get(map,"p");
		String q = get(map,"q");
		String x = get(map,"x");
		
		DSAPrivateKeySpec keySpec = new DSAPrivateKeySpec(big(x),big(p),big(q),big(g));
		return kf.generatePrivate(keySpec);
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	private BigInteger big(String value)
	{return new BigInteger(value);}
}
