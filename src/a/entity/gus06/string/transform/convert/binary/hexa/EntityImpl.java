package a.entity.gus06.string.transform.convert.binary.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toHexa;
	private Service readBinary;


	public EntityImpl() throws Exception
	{
		toHexa = Outside.service(this,"gus.x.bytearraytohexa1");
		readBinary = Outside.service(this,"gus06.convert.stringtobytearray.binary");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toHexa.t(readBinary.t(s));
	}
}
