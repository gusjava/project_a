package a.entity.gus06.list.build.from.iterator.limit;

import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Iterator it = (Iterator) o[0];
		Integer limit = (Integer) o[1];
		
		List list = new ArrayList();
		for(int i=0;i<limit;i++)
		list.add(it.hasNext() ? it.next() : null);
		
		return list;
	}
}
