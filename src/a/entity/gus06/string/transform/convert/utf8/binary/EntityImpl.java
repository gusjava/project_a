package a.entity.gus06.string.transform.convert.utf8.binary;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toBinary;
	private Service readUTF8;


	public EntityImpl() throws Exception
	{
		toBinary = Outside.service(this,"gus06.tostring.bytetobinary");
		readUTF8 = Outside.service(this,"gus06.convert.stringtobytearray.utf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toBinary.t(readUTF8.t(s));
	}
}
