package a.entity.gus06.collection.contains.impl.eq;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180123";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Collection c = (Collection) o[0];
		Object element = o[1];
		
		Iterator it = c.iterator();
		while(it.hasNext()) if(it.next()==element) return true;
		return false;
	}
}
