package a.entity.gus06.map.findall3;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170112";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.findall3.buildmap");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F filter = (F) o[1];
		
		Map output = new HashMap();
		
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Map m = (Map) buildMap.t(new Object[]{input,key});
			if(filter.f(m)) output.put(key,input.get(key));
		}
		return output;
	}
}
