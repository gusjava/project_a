package a.entity.gus06.string.transform.normalize.diacritics;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141023";}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return DIACRITICS.normalize((String)obj);
	}
}
