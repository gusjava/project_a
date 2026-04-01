package a.entity.gus06.data.filter.istype.intpp;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160818";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		if(!(obj instanceof Integer)) return false;
		
		int v = ((Integer) obj).intValue();
		return v>0;
	}
	
}
