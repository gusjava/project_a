package a.entity.gus06.crypto.hash.sha224.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}

	private Service sha256;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		sha256 = Outside.service(this,"gus06.crypto.hash.sha224");
		hexa = Outside.service(this,"gus06.tostring.bytetohexa");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(sha256.t(obj));}
}
