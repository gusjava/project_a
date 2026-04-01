package a.entity.gus06.sys.expression1.apply.op._is_collection;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return Boolean.FALSE;
		return Boolean.valueOf(obj instanceof Collection);
	}
}
