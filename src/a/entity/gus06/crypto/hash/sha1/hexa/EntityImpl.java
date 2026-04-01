package a.entity.gus06.crypto.hash.sha1.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	private Service sha1;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		sha1 = Outside.service(this,"gus06.crypto.hash.sha1");
		hexa = Outside.service(this,"gus06.tostring.bytetohexa");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(sha1.t(obj));}
}
