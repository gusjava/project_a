package a.entity.gus06.crypto.asymkeys.signature.dsa.verify.hexa;

import a.framework.*;
import java.security.PublicKey;
import java.security.Signature;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141014";}


	private Service findByteArray;
	private Service hexaToBA;
	
	private Signature si;
	
	
	public EntityImpl() throws Exception
	{
		findByteArray = Outside.service(this,"gus06.find.bytearray");
		hexaToBA = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
		
		si = Signature.getInstance("DSA");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		PublicKey key = (PublicKey) o[0];
		byte[] input = (byte[]) findByteArray.t(o[1]);
		byte[] sign = (byte[]) hexaToBA.t(o[2]);
		
		si.initVerify(key);
		si.update(input);
		return si.verify(sign);
	}
}
