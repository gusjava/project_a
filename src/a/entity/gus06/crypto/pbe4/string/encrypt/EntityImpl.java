package a.entity.gus06.crypto.pbe4.string.encrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service encrypt;
	private Service getKey;
	
	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe.string.encrypt.hexa");
		getKey = Outside.service(this,"gus06.crypto.pbe4.secretkey.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		return encrypt.t(getKey.t(obj));
	}
}