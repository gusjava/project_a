package a.entity.gus06.data.filter.istype.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160818";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return obj instanceof File;
	}
	
}
