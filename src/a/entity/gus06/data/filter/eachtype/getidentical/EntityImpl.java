package a.entity.gus06.data.filter.eachtype.getidentical;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231016";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Collection)
			return getIdentical((Collection) obj);
		if(obj instanceof Object[])
			return getIdentical((Object[]) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Class getIdentical(Collection c)
	{
		if(c.isEmpty()) return null;
		Iterator it = c.iterator();
		Class c0 = it.next().getClass();
		while(it.hasNext())
		if(!it.next().getClass().equals(c0)) return null;
		return c0;
	}
	
	private Class getIdentical(Object[] oo)
	{
		if(oo.length==0) return null;
		Class c0 = oo[0].getClass();
		for(int i=1;i<oo.length;i++)
		if(!oo[i].getClass().equals(c0)) return null;
		return c0;
	}
}