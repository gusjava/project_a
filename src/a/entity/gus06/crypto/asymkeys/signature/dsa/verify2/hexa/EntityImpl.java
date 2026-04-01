package a.entity.gus06.crypto.asymkeys.signature.dsa.verify2.hexa;

import a.framework.*;
import java.security.PublicKey;
import java.security.Signature;
import java.io.InputStream;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141015";}


	private Service findInputStream;
	private Service hexaToBA;
	
	private Signature si;
	
	
	public EntityImpl() throws Exception
	{
		findInputStream = Outside.service(this,"gus06.find.inputstream");
		hexaToBA = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
		
		si = Signature.getInstance("DSA");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		PublicKey key = (PublicKey) o[0];
		InputStream is = (InputStream) findInputStream.t(o[1]);
		byte[] sign = (byte[]) hexaToBA.t(o[2]);
		
		si.initVerify(key);
		
		int b;
		byte[] data = new byte[2048];
		while((b = is.read(data,0,2048))!=-1)
		{si.update(data,0,b);}
		
		return si.verify(sign);
	}
}
