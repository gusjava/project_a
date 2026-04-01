package a.entity.gus06.string.transform.convert.binary.base64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBase64;
	private Service readBinary;


	public EntityImpl() throws Exception
	{
		toBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
		readBinary = Outside.service(this,"gus06.convert.stringtobytearray.binary");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBase64.t(readBinary.t(s));
	}
}
