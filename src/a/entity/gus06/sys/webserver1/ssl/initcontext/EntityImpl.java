package a.entity.gus06.sys.webserver1.ssl.initcontext;

import a.framework.*;
import java.security.KeyStore;
import java.io.File;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.KeyManagerFactory;
import java.io.FileInputStream;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141022";}


	private File storeDir;
	private File keyStoreFile;
	private File trustStoreFile;

	public EntityImpl() throws Exception
	{
		storeDir = (File) Outside.resource(this,"defaultdir");
		keyStoreFile = new File(storeDir,"testkeys");
		trustStoreFile = new File(storeDir,"testkeys");
	}
	
	
	
	
	public Object g() throws Exception
	{
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore ts = KeyStore.getInstance("JKS");

		char[] passphrase = "passphrase".toCharArray();

		ks.load(new FileInputStream(keyStoreFile), passphrase);
		ts.load(new FileInputStream(trustStoreFile), passphrase);

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, passphrase);

		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(ts);

		SSLContext ctx = SSLContext.getInstance("TLS");

		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		return ctx;
	}
	
}
