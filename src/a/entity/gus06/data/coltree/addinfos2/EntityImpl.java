package a.entity.gus06.data.coltree.addinfos2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161026";}
	
	public static final String CHILDREN = "children";
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map root = o[0];
		Map filters = o[1];
		
		handle(root,filters);
	}
	
	
	
	private void handle(Map map, Map filters) throws Exception
	{
		List children = map.containsKey(CHILDREN) ? (List) map.get(CHILDREN) : null;
		
		if(children!=null)
		for(Object child:children) handle((Map) child,filters);
			
		Iterator it = filters.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			F filter = (F) filters.get(name);
			int count = filter.f(map) ? 1 : 0;
			
			if(children!=null)
			for(Object child:children)
			{
				Map m = (Map) child;
				if(m.containsKey(name))
				{
					Integer n = (Integer) m.get(name);
					count += n.intValue();
				}
			}
			
			if(count>0)
			map.put(name,Integer.valueOf(count));
		}
	}
}
