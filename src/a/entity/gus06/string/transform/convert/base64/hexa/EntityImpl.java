package a.entity.gus06.string.transform.convert.base64.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toHexa;
	private Service readBase64;


	public EntityImpl() throws Exception
	{
		toHexa = Outside.service(this,"gus06.tostring.bytetohexa");
		readBase64 = Outside.service(this,"gus06.convert.stringtobytearray.base64");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toHexa.t(readBase64.t(s));
	}
}
