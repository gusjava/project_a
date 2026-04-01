package a.entity.gus06.data.filter.eachtype.identical;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231016";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof Collection)
			return checkIdentical((Collection) obj);
		if(obj instanceof Object[])
			return checkIdentical((Object[]) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean checkIdentical(Collection c)
	{
		if(c.size()<2) return true;
		Iterator it = c.iterator();
		Class c0 = it.next().getClass();
		while(it.hasNext())
		if(!it.next().getClass().equals(c0)) return false;
		return true;
	}
	
	private boolean checkIdentical(Object[] oo)
	{
		if(oo.length<2) return true;
		Class c0 = oo[0].getClass();
		for(int i=1;i<oo.length;i++)
		if(!oo[i].getClass().equals(c0)) return false;
		return true;
	}
}