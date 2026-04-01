package a.entity.gus06.sys.crypto.pseudo.generator;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140707";}


	
	private Service generator;
	private Service buildSign;
	private Service privateKeyToSpec;
	private Service publicKeyToSpec;
	
	
	public EntityImpl() throws Exception
	{
		generator = Outside.service(this,"gus06.crypto.asymkeys.generator.dsa1024");
		buildSign = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.sign.hexa");
		privateKeyToSpec = Outside.service(this,"gus06.crypto.asymkeys.privatekey.keyspec.dsa");
		publicKeyToSpec = Outside.service(this,"gus06.crypto.asymkeys.publickey.keyspec.dsa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String owner = (String) obj;
		
		KeyPair kp = (KeyPair) generator.g();
		
		PrivateKey key_pr = kp.getPrivate();
		PublicKey key_pu = kp.getPublic();
		
		String sign = sign(key_pr,owner);
		
		DSAPrivateKeySpec spec_pr = (DSAPrivateKeySpec) privateKeyToSpec.t(key_pr);
		DSAPublicKeySpec spec_pu = (DSAPublicKeySpec)  publicKeyToSpec.t(key_pu);
		
		
		Properties prop_pu = new Properties();
		
		prop_pu.setProperty("p",""+spec_pu.getP()); // PRIME
		prop_pu.setProperty("q",""+spec_pu.getQ()); // SUB PRIME
		prop_pu.setProperty("g",""+spec_pu.getG()); // BASE
		prop_pu.setProperty("y",""+spec_pu.getY()); // PUBLIC KEY
		prop_pu.setProperty("owner",owner);
		prop_pu.setProperty("sign",sign);
		
		Properties prop_pr = new Properties();
		
		prop_pr.putAll(prop_pu);
		prop_pr.setProperty("x",""+spec_pr.getX()); // PRIVATE KEY
		
		return new Properties[]{prop_pu,prop_pr};
	}
	
	
	private String sign(PrivateKey k, String s) throws Exception
	{return (String) buildSign.t(new Object[]{k,s});}
} 
