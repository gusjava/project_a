package a.entity.gus06.string.transform.convert.hexa.base64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBase64;
	private Service readHexa;


	public EntityImpl() throws Exception
	{
		toBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
		readHexa = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBase64.t(readHexa.t(s));
	}
}
