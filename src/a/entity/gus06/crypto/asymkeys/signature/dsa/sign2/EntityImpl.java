package a.entity.gus06.crypto.asymkeys.signature.dsa.sign2;

import java.security.PrivateKey;
import java.security.Signature;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141015";}


	private Service findInputStream;
	private Signature si;
	
	
	public EntityImpl() throws Exception
	{
		findInputStream = Outside.service(this,"gus06.find.inputstream");
		si = Signature.getInstance("DSA");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		PrivateKey key = (PrivateKey) o[0];
		InputStream is = (InputStream) findInputStream.t(o[1]);
		
		si.initSign(key);
		
		int b;
		byte[] data = new byte[2048];
		while((b = is.read(data,0,2048))!=-1)
		{si.update(data,0,b);}
		
		return si.sign();
	}
} 
