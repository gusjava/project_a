package a.entity.gus06.crypto.hash.sha384.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	private Service sha384;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		sha384 = Outside.service(this,"gus06.crypto.hash.sha384");
		hexa = Outside.service(this,"gus06.tostring.bytetohexa");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(sha384.t(obj));}
}
