package a.entity.gus06.sys.jdbcanalyzer1.fkmap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170517";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map fkmap0 = (Map) o[0];
		Map fkmap1 = (Map) o[1];
		
		Map m = new HashMap(fkmap1);
		if(fkmap0==null) return m;
		
		Iterator it = fkmap0.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = fkmap0.get(key);
			
			if(m.containsKey(key))
			{
				if(!m.get(key).equals(value))
				throw new Exception("Invalid fk rule inside fkmap0: "+key+"="+value);
			}
			else m.put(key,value);
		}
		return m;
	}
}
