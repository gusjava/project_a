package a.entity.gus06.feature.apply.gobj;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof G) return ((G) obj).g();
		return obj;
	}
}