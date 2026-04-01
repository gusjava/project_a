package a.entity.gus06.crypto.pbe2.bytearray.encrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service encrypt;
	private Service getKey;
	private T t;
	
	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe.bytearray.encrypt");
		getKey = Outside.service(this,"gus06.crypto.pbe2.secretkey.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(t==null) t = (T) encrypt.t(getKey.g());
		return t.t(obj);
	}
}