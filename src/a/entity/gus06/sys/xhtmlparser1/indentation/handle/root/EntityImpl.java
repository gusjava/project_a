package a.entity.gus06.sys.xhtmlparser1.indentation.handle.root;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170226";}
	
	public static final String K_CONTENT = "content";


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		StringBuffer b = (StringBuffer) o[0];
		Map map = (Map) o[1];
		String offset = (String) o[2];
		P p = (P) o[3];
		
		List content = (List) get1(map,K_CONTENT);
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			p.p(new Object[]{b,child,""});
		}
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return  map.get(key);
	}
}
