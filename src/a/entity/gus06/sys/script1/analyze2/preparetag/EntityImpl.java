package a.entity.gus06.sys.script1.analyze2.preparetag;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150829";}
	
	
	public static final String K_TYPE = "type";
	public static final String K_VALUE = "value";
	public static final String K_NAME = "name";
	public static final String K_UNTIL = "until";
	public static final String K_PARAMS = "params";
	public static final String K_EXECUTOR = "executor";
	
	public static final String T_ROOT = "root";
	public static final String T_ELEMENT = "element";
	
	

	private Service findUntil;
	private Service formatValue;
	private Service findExecutor;
	
	
	public EntityImpl() throws Exception
	{
		findUntil = Outside.service(this,"gus06.sys.script1.analyze2.finduntil");
		formatValue = Outside.service(this,"gus06.sys.script1.analyze2.formatvalue");
		findExecutor = Outside.service(this,"gus06.sys.script1.executor.find");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		String type = get(tag,K_TYPE);
		String value = get(tag,K_VALUE);
		
		if(type==null) throw new Exception("No type found inside tag");
		
		if(type.equals(T_ROOT))
		{
			put(tag,K_NAME,T_ROOT);
			put(tag,K_UNTIL,until(T_ROOT));
		}
		else if(type.equals(T_ELEMENT))
		{
			value = (String) formatValue.t(value);
			String[] n = value.split("[ \n\r\t]+",2);
			
			String name = n[0];
			String params = n.length==2?n[1]:null;
			String until = until(name);
			
			put(tag,K_NAME,name);
			put(tag,K_PARAMS,params);
			put(tag,K_UNTIL,until);
		}
		
		Object executor = findExecutor.t(tag);
		put(tag,K_EXECUTOR,executor);
	}
	
	
	
	
	
	
	private String until(String name) throws Exception
	{return (String) findUntil.t(name);}
	
	
	
	private String get(Map map, String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
}
