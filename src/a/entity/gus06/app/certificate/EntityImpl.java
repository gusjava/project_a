package a.entity.gus06.app.certificate;

import a.framework.*;
import java.util.Map;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.io.InputStream;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141005";}

	public static final String PROP_BUILDID = "jar.buildid";
	public static final String PROP_PASSWORD = "keystore.pwd";
	public static final String PATH_KEYSTORE = "keystore/keystore.jks";


	private Service inside;
	private Map prop;
	private Certificate cert;
	
	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"inside");
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	public Object g() throws Exception
	{
		if(cert==null) init();
		return cert;
	}
	
	private void init() throws Exception
	{
		String buildId = get(PROP_BUILDID);
		String pwd = get(PROP_PASSWORD);
		
		InputStream is = (InputStream) inside.t("stream."+PATH_KEYSTORE);
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
	    	keystore.load(is,pwd.toCharArray());
	
		cert = keystore.getCertificate(buildId);
		if(cert==null) throw new Exception("No certificat found for alias: "+buildId);
	}
	
	
	
	private String get(String key) throws Exception
	{
		if(!prop.containsKey(key)) throw new Exception("Property not found: "+key);
		return (String) prop.get(key);
	}
}
