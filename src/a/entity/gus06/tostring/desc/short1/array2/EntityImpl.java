package a.entity.gus06.tostring.desc.short1.array2;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180117";}



	public Object t(Object obj) throws Exception
	{
		Object[][] a = (Object[][]) obj;
		
		int nb1 = a.length;
		int nb2 = nb1>0 ? a[0].length : 0;
		
		return className(a)+" ["+nb1+","+nb2+"]";
	}
	
	private String className(Object obj)
	{return obj.getClass().getSimpleName();}
}
