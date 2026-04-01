package a.entity.gus06.sys.expression1.builder2.t.map;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170605";}


	private Service builder;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map cMap = (Map) o[0];
		Map opMap = (Map) o[1];
		
		Map cMap1 = new HashMap();
		Iterator it = cMap.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = cMap.get(key);
			
			T t = (T) builder.t(new Object[]{value,opMap});
			cMap1.put(key,t);
		}
		return cMap1;
	}
}
