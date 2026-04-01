package a.entity.gus06.feature.apply.tobj;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0]==null) return null;
		if(o[0] instanceof T) return ((T)o[0]).t(o[1]);
		return o[0];
	}
}