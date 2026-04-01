package a.entity.gus06.string.transform.convert.base64.binary;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBinary;
	private Service readBase64;


	public EntityImpl() throws Exception
	{
		toBinary = Outside.service(this,"gus06.tostring.bytetobinary");
		readBase64 = Outside.service(this,"gus06.convert.stringtobytearray.base64");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBinary.t(readBase64.t(s));
	}
}
