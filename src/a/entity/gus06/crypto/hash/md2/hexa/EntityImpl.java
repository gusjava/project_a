package a.entity.gus06.crypto.hash.md2.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}

	private Service md2;
	private Service hexa;

	public EntityImpl() throws Exception
	{
		md2 = Outside.service(this,"gus06.crypto.hash.md2");
		hexa = Outside.service(this,"gus.x.bytearraytohexa1");
	}
	
	public Object t(Object obj) throws Exception
	{return hexa.t(md2.t(obj));}
}
