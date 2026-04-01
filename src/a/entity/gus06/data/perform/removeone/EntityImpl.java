package a.entity.gus06.data.perform.removeone;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20160122";}

	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof Collection)
		{
			Collection c = (Collection) obj;
			if(c.isEmpty()) return false;
			remove(c);
			return true;
		}
		if(obj instanceof Map)
		{
			Map m = (Map) obj;
			if(m.isEmpty()) return false;
			remove(m.keySet());
			return true;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void remove(Collection c)
	{
		Iterator it = c.iterator();
		it.next();
		it.remove();
	}
}
