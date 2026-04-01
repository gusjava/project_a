package a.entity.gus06.string.transform.format.html.rm.comments;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191220";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.replaceAll("(?s)<!--.*?-->"," ");
	}
}
