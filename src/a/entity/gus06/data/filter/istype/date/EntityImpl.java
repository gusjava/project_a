package a.entity.gus06.data.filter.istype.date;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160818";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return obj instanceof Date;
	}
	
}
