package a.entity.gus06.set.build.from.iterator;

import a.framework.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}

	
	
	public Object t(Object obj) throws Exception
	{
		Iterator it = (Iterator) obj;
		Set set = new HashSet();
		while(it.hasNext()) set.add(it.next());
		return set;
	}
}
