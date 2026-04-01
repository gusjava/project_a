package a.entity.gus06.convert.stringtobytearray.utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161015";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String) obj);
	}

	private byte[] convert(String s) throws Exception
	{
		return s.getBytes("UTF-8");
	}
}
