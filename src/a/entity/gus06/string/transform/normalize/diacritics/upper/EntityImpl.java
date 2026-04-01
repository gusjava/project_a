package a.entity.gus06.string.transform.normalize.diacritics.upper;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141023";}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return DIACRITICS_UPPERCASE.normalize((String)obj);
	}
}
