package a.entity.gus06.sys.webserver1.web2.zdyn.e.find.prop1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}


	private Service formatter;


	public EntityImpl() throws Exception
	{
		formatter = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.formatter1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String rule = (String) o[1];
		
		String[] n = rule.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:null;
		
		Map prop = (Map) mr.r("data config prop");
		String value = prop.containsKey(key)?(String) prop.get(key):dValue;
		if(value==null) return null;
		
		return formatter.t(new Object[]{mr,value});
	}
}
