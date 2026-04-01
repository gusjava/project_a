package a.entity.gus06.sys.textbuilder1.generator;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160301";}

	public static final int LIMIT = 300;
	
	private Service formatChecker;
	private Service findDefaults;
	
	public EntityImpl() throws Exception
	{
		formatChecker = Outside.service(this,"gus06.data.string.formatchecker1");
		findDefaults = Outside.service(this,"gus06.sys.textbuilder1.parser.finddefaults");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map mapRules = (Map) o[0];
		Map mapDefault = (Map) o[1];
		String key = (String) o[2];
		
		return new T1(mapRules,mapDefault,key);
	}
	
	
	private class T1 implements T
	{
		private Map mapRules;
		private Map mapDefault;
		private Map mapDefault0;
		private String key;
		
		public T1(Map mapRules, Map mapDefault, String key) throws Exception
		{
			this.mapRules = mapRules;
			this.mapDefault = mapDefault;
			this.mapDefault0 = (Map) findDefaults.t(mapRules);
			this.key = key;
		}
		
		public Object t(Object obj) throws Exception
		{
			Map map = toMap(obj);
			if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
			String name = (String) map.get(key);
			
			Map map1 = new HashMap();
			
			map1.putAll(mapDefault0);
			map1.putAll(mapDefault);
			map1.putAll(map);
			
			return buildText(0,mapRules,map1,name);
		}
		
		private Map toMap(Object obj) throws Exception
		{
			if(obj instanceof Map) return (Map) obj;
			if(obj instanceof String) return stringToMap((String) obj);
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private Map stringToMap(String s)
		{
			Map map = new HashMap();
			map.put(key,s);
			return map;
		}
	}
	
	
	
	private String buildText(int l, Map mapRules, Map mapValues, String name) throws Exception
	{
		if(l>LIMIT) throw new Exception("Limit exceeded for text building looping: "+l);
		
		if(!mapRules.containsKey(name))
			throw new Exception("Key not found inside rules map: "+name);
		String rule = (String) mapRules.get(name);
		
		Iterator it = mapRules.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(rule.contains("["+key+"]"))
			{
				String value = buildText(l+1,mapRules,mapValues,key);
				rule = rule.replace("["+key+"]",value);
			}
		}
		
		it = mapValues.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(rule.contains("["+key+"]"))
			{
				String value = ""+mapValues.get(key);
				checkFormat(mapRules,key,value);
				rule = rule.replace("["+key+"]",value);
			}
		}
		
		return rule;
	}
	
	
	
	
	
	private void checkFormat(Map mapRules, String key, String value) throws Exception
	{
		if(!mapRules.containsKey("format."+key)) return;
		String format = (String) mapRules.get("format."+key);
		
		if(format.startsWith(">"))
		{
			format = format.substring(1);
			if(!mapRules.containsKey("format."+format))
				throw new Exception("Format definition not found: "+format);
			format = (String) mapRules.get("format."+format);
		}
		
		if(!formatChecker.f(new String[]{value,format}))
			throw new Exception("Invalid format for data "+key+" : "+value);
	}
}
