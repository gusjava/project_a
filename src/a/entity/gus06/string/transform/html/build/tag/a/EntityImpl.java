package a.entity.gus06.string.transform.html.build.tag.a;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210630";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return "<a target=\"_blank\" href=\""+s+"\">"+s+"</a>";
	}
}