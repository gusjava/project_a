package a.entity.gus06.string.transform.convert.hexa.utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200419";}


	private Service toUTF8;
	private Service readHexa;


	public EntityImpl() throws Exception
	{
		toUTF8 = Outside.service(this,"gus06.convert.bytearraytoutf8");
		readHexa = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return toUTF8.t(readHexa.t(s));
	}
}
