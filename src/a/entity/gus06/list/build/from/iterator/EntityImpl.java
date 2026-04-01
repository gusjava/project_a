package a.entity.gus06.list.build.from.iterator;

import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}

	public static int  LIMIT = 100000;
	
	
	public Object t(Object obj) throws Exception
	{
		Iterator it = (Iterator) obj;
		List list = new ArrayList();
		
		int k = 0;
		while(it.hasNext())
		{
			k++;
			if(k>LIMIT) throw new Exception("Limit exceeded: "+k);
			list.add(it.next());
		}
		return list;
	}
}
