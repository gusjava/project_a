package a.entity.gus06.map.build.fromset;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}


	private Service analyze;

	public EntityImpl() throws Exception
	{
		analyze = Outside.service(this,"gus06.map.build.fromlist.analyzeelem");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Set set = (Set) obj;
		Map map = new HashMap();
		
		Iterator it = set.iterator();
		while(it.hasNext());
		{
			Object element = it.next();
			Object[] n = (Object[]) analyze.t(element);
			map.put(n[0],n[1]);
		}
		return map;
	}
}
