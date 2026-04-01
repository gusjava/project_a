package a.entity.gus06.convert.booleantointeger;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		boolean v = ((Boolean)obj).booleanValue();
		return Integer.valueOf(v ? 1 : 0);
	}
}
