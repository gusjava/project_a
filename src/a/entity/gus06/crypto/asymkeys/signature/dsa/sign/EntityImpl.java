package a.entity.gus06.crypto.asymkeys.signature.dsa.sign;

import java.security.PrivateKey;
import java.security.Signature;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}


	private Service findByteArray;
	private Signature si;
	
	
	public EntityImpl() throws Exception
	{
		findByteArray = Outside.service(this,"gus06.find.bytearray");
		si = Signature.getInstance("DSA");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		PrivateKey key = (PrivateKey) o[0];
		byte[] input = (byte[]) findByteArray.t(o[1]);
		
		si.initSign(key);
		si.update(input);
		return si.sign();
	}
} 
