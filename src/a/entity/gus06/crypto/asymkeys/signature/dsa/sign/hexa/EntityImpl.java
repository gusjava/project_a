package a.entity.gus06.crypto.asymkeys.signature.dsa.sign.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}


	private Service sign;
	private Service toHexa;


	public EntityImpl() throws Exception
	{
		sign = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.sign");
		toHexa = Outside.service(this,"gus.x.bytearraytohexa1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		byte[] b = (byte[]) sign.t(obj);
		return toHexa.t(b);
	}
}
