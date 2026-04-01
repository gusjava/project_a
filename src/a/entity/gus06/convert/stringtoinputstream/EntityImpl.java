package a.entity.gus06.convert.stringtoinputstream;

import a.framework.*;
import java.io.ByteArrayInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161015";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		byte[] b = s.getBytes();
		return new ByteArrayInputStream(b);
	}
}
