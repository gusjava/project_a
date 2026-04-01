package a.entity.gus06.map.string.replacer;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190429";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String seq = (String) o[1];
		String repl = (String) o[2];
		
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			
			String key1 = replace(key,seq,repl);
			String value1 = replace(value,seq,repl);
			
			if(map1.containsKey(key1)) throw new Exception("Duplicated key found: "+key1);
			map1.put(key1,value1);
		}
		
		map.clear();
		map.putAll(map1);
	}
	
	
	private String replace(String s, String seq, String repl)
	{return s.replace(seq,repl);}
}
