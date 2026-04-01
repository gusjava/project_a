package a.entity.gus06.find.charset;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170525";}


	private Service stringToCharset;

	public EntityImpl() throws Exception
	{
		stringToCharset = Outside.service(this,"gus06.convert.stringtocharset");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Charset) return obj;
		if(obj instanceof String) return stringToCharset.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
		
}
