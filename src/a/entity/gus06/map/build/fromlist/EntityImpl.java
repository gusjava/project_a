package a.entity.gus06.map.build.fromlist;

import a.framework.*;
import java.util.List;
import java.util.Map;
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
		List list = (List) obj;
		Map map = new HashMap();
		
		for(int i=0;i<list.size();i++)
		{
			Object element = list.get(i);
			Object[] n = (Object[]) analyze.t(element);
			map.put(n[0],n[1]);
		}
		return map;
	}
}
