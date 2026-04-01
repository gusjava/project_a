package a.entity.gus06.crypto.pbe1.secretkey.holder;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150625";}


	private Service keyBuilder;
	private Object key;
	
	public EntityImpl() throws Exception
	{
		keyBuilder = Outside.service(this,"gus06.crypto.pbe.secretkey.builder");
	}
	

	public Object g() throws Exception
	{
		if(key==null) initKey();
		return key;
	}
	
	private void initKey() throws Exception
	{key = keyBuilder.t("sdf9bfvipq987Vfservc43qq");}
}
