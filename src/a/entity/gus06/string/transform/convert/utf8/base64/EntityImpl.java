package a.entity.gus06.string.transform.convert.utf8.base64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBase64;
	private Service readUTF8;


	public EntityImpl() throws Exception
	{
		toBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
		readUTF8 = Outside.service(this,"gus06.convert.stringtobytearray.utf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBase64.t(readUTF8.t(s));
	}
}
