package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.arg;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		return get(args,info);
	}
	
	
	
	private String get(Map map, String info)
	{
		String[] n = info.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:"?";
		
		if(!map.containsKey(key)) return dValue;
		return (String) map.get(key);
	}
}
