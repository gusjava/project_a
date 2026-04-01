package a.entity.gus06.file.properties.perform.apply.script1.build.other;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150925";}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return buildOther((Map) o[0], (Map) o[1]);
	}
	
	
	
	private Map buildOther(Map prop, Map rules)
	{
		Map other = new HashMap();
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			if(!rules.containsKey(key)) other.put(key,value);
		}
		return other;
	}
}
