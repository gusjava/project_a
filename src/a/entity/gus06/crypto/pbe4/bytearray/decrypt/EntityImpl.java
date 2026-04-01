package a.entity.gus06.crypto.pbe4.bytearray.decrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service decrypt;
	private Service getKey;
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe.bytearray.decrypt");
		getKey = Outside.service(this,"gus06.crypto.pbe4.secretkey.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		return decrypt.t(getKey.t(obj));
	}
}
