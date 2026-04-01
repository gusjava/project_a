package a.entity.gus06.tostring.desc.short1.collection;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}



	public Object t(Object obj) throws Exception
	{
		Collection c = (Collection) obj;
		return className(c)+" ["+c.size()+"]";
	}
	
	private String className(Object obj)
	{return obj.getClass().getSimpleName();}
}
