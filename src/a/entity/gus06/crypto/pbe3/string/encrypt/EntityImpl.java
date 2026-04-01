package a.entity.gus06.crypto.pbe3.string.encrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service encrypt;
	private Service getKey;

	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe.string.encrypt.hexa");
		getKey = Outside.service(this,"gus06.crypto.pbe3.secretkey.holder");
	}
	
	public Object t(Object obj) throws Exception
	{
		T t = (T) encrypt.t(getKey.g());
		return t.t(obj);
	}
}