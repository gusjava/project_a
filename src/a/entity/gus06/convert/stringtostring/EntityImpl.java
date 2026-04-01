package a.entity.gus06.convert.stringtostring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}

	
	public Object t(Object obj) throws Exception
	{
		return (String) obj;
	}
}
