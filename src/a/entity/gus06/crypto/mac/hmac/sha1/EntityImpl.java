package a.entity.gus06.crypto.mac.hmac.sha1;

import a.framework.*;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}

	public static final String ALGO = "HmacSHA1";

	private Service toByteArray;

	public EntityImpl() throws Exception
	{
		toByteArray = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		byte[] key = toByteArray(obj);
		return new Holder(key);
	}
	
	private byte[] toByteArray(Object obj) throws Exception
	{
		if(obj instanceof byte[]) return (byte[]) obj;
		if(obj instanceof String) return (byte[]) toByteArray.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Holder implements T
	{
		private byte[] key;
		public Holder(byte[] key) {this.key = key;}
		
		public Object t(Object obj) throws Exception
		{return encrypt(key,(byte[]) obj);}
	}
	
	
	private byte[] encrypt(byte[] key, byte[] data) throws Exception
	{
		Mac oHmac = Mac.getInstance(ALGO);
		SecretKeySpec sks = new SecretKeySpec(key, oHmac.getAlgorithm());
		SecretKey sk = (SecretKey) sks;
		
		oHmac.init(sk);
		oHmac.reset();
		byte[] output = oHmac.doFinal(data);
		oHmac.reset();
		
		return output;
	}
}