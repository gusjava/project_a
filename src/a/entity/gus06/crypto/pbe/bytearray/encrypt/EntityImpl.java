package a.entity.gus06.crypto.pbe.bytearray.encrypt;

import java.security.AlgorithmParameters;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150625";}

	public static final String ALGO = "PBEWithMD5AndDES";
	
	private Service keyBuilder;
	private Service keyParams;
	
	private AlgorithmParameters params;
	private Cipher cipher;
	
	
	public EntityImpl() throws Exception
	{
		keyBuilder = Outside.service(this,"gus06.crypto.pbe.secretkey.builder");
		keyParams = Outside.service(this,"gus06.crypto.pbe.secretkey.params");
		
		params = (AlgorithmParameters) keyParams.g();
		cipher = Cipher.getInstance(ALGO);
	}



	public Object t(Object obj) throws Exception
	{
		SecretKey key = buildKey(obj);
		return new Holder(key);
	}
	
	
	private SecretKey buildKey(Object obj) throws Exception
	{
		if(obj instanceof SecretKey) return (SecretKey) obj;
		if(obj instanceof String) return (SecretKey) keyBuilder.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	
	
	private class Holder implements T
	{
		private SecretKey key;
		public Holder(SecretKey key) {this.key = key;}
		
		public Object t(Object obj) throws Exception
		{return encrypt(key,(byte[]) obj);}
	}
	
	
	private byte[] encrypt(SecretKey key, byte[] b) throws Exception
	{
		cipher.init(Cipher.ENCRYPT_MODE,key,params);
		return cipher.doFinal(b);
	}
}
