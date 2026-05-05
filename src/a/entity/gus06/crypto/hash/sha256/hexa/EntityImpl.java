package a.entity.gus06.crypto.hash.sha256.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	private Service sha256;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		sha256 = Outside.service(this,"gus.x.crypto.hash.sha256");
		hexa = Outside.service(this,"gus.x.bytearraytohexa1");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(sha256.t(obj));}
}
