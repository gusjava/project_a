package a.entity.gus06.map.build.fromarray;

import a.framework.*;
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
		Object[] array = (Object[]) obj;
		Map map = new HashMap();
		
		for(int i=0;i<array.length;i++)
		{
			Object element = array[i];
			Object[] n = (Object[]) analyze.t(element);
			map.put(n[0],n[1]);
		}
		return map;
	}
}
