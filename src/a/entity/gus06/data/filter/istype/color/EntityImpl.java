package a.entity.gus06.data.filter.istype.color;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160818";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return obj instanceof Color;
	}
	
}
