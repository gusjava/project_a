package a.entity.gus06.crypto.pbe2.secretkey.holder;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151115";}


	private Service keyBuilder;
	private Object key;
	
	public EntityImpl() throws Exception
	{
		keyBuilder = Outside.service(this,"gus06.crypto.pbe.secretkey.builder.askpwd");
	}
	
	public Object g() throws Exception
	{
		if(key==null) key = keyBuilder.g();
		return key;
	}
}
