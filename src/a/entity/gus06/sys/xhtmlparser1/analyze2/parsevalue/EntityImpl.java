package a.entity.gus06.sys.xhtmlparser1.analyze2.parsevalue;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170404";}
	
	public static final String K_PARAMS = "params";
	public static final String K_NAME = "name";


	private Service buildParams;
	
	public EntityImpl() throws Exception
	{
		buildParams = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2.buildparams");
	}
	
		
	
	public Object t(Object obj) throws Exception
	{
		String value = (String) obj;
		
		value = value.trim();
		if(value.startsWith("<")) value = value.substring(1);
		if(value.endsWith(">")) value = value.substring(0,value.length()-1);
		value = value.trim();
		if(value.endsWith("/")) value = value.substring(0,value.length()-1);
		value = value.trim();
		
		String[] n = value.split("[ \n\r\t]+",2);
		
		String name = n[0];
		String params = n.length==2?n[1]:null;
		Map paramsMap = (Map) buildParams.t(params);
		
		Map tag = new HashMap();
		put(tag,K_NAME,name);
		put(tag,K_PARAMS,paramsMap);
		
		return tag;
	}
	
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
}
