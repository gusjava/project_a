package a.entity.gus06.string.transform.convert.hexa.binary;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBinary;
	private Service readHexa;


	public EntityImpl() throws Exception
	{
		toBinary = Outside.service(this,"gus06.tostring.bytetobinary");
		readHexa = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBinary.t(readHexa.t(s));
	}
}
