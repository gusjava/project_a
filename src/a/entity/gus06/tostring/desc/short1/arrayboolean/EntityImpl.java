package a.entity.gus06.tostring.desc.short1.arrayboolean;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180117";}



	public Object t(Object obj) throws Exception
	{
		boolean[] a = (boolean[]) obj;
		return className(a)+" ["+a.length+"]";
	}
	
	private String className(Object obj)
	{return obj.getClass().getSimpleName();}
}
