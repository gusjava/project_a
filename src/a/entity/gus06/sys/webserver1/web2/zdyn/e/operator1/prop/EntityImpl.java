package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator1.prop;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		String[] n = info.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:"?";
		
		Map prop = (Map) mr.r("data config prop");
		
		if(!prop.containsKey(key)) return dValue;
		return prop.get(key);
	}
}
