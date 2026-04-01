package a.entity.gus06.convert.stringtoinputstream.utf8;

import a.framework.*;
import java.io.ByteArrayInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		byte[] b = s.getBytes("UTF-8");
		return new ByteArrayInputStream(b);
	}
}
