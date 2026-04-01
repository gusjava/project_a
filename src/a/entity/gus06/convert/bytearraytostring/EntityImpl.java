package a.entity.gus06.convert.bytearraytostring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191007";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return new String((byte[]) obj);
	}
}
