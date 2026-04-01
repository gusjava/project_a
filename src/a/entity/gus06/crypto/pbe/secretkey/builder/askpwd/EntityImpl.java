package a.entity.gus06.crypto.pbe.secretkey.builder.askpwd;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170520";}


	private Service keyBuilder;
	private Service askPwd;
	
	public EntityImpl() throws Exception
	{
		keyBuilder = Outside.service(this,"gus06.crypto.pbe.secretkey.builder");
		askPwd = Outside.service(this,"gus06.security.askinfo.password1");
	}
	
	public Object g() throws Exception
	{return keyBuilder.t(askPwd.g());}
}