package a.entity.gus06.crypto.pbe1.bytearray.decrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}


	private Service decrypt;
	private Service getKey;
	private T t;
	
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe.bytearray.decrypt");
		getKey = Outside.service(this,"gus06.crypto.pbe1.secretkey.holder");
		t = (T) decrypt.t(getKey.g());
	}
	
	public Object t(Object obj) throws Exception
	{return t.t(obj);}
}
