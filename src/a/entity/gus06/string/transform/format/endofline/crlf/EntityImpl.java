package a.entity.gus06.string.transform.format.endofline.crlf;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251202";}

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.replace("\n","\r\n").replace("\r\r","\r");
	}
}
