package a.entity.gus06.convert.stringtocharset;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170525";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("default")) return Charset.defaultCharset();
		return Charset.forName(s);
	}
}
