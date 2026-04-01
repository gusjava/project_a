package a.entity.gus06.string.transform.convert.base64.utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toUTF8;
	private Service readBase64;


	public EntityImpl() throws Exception
	{
		toUTF8 = Outside.service(this,"gus06.convert.bytearraytoutf8");
		readBase64 = Outside.service(this,"gus06.convert.stringtobytearray.base64");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toUTF8.t(readBase64.t(s));
	}
}
