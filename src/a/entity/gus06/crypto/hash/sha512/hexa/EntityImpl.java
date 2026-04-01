package a.entity.gus06.crypto.hash.sha512.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	private Service sha512;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		sha512 = Outside.service(this,"gus06.crypto.hash.sha512");
		hexa = Outside.service(this,"gus06.tostring.bytetohexa");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(sha512.t(obj));}
}
