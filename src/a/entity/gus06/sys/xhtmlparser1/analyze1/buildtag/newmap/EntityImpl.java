package a.entity.gus06.sys.xhtmlparser1.analyze1.buildtag.newmap;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20170131";}
	
	
	public static final String T_ROOT = "root";
	public static final String T_ELEMENT = "element";
	public static final String T_TEXT = "text";
	
	public static final String K_TYPE = "type";
	public static final String K_NAME = "name";
	public static final String K_VALUE = "value";
	public static final String K_ID = "id";
	
	public static final int TRUNC_LIMIT = 40;


	private Service buildMap;
	private Service generateId;

	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.map2");
		generateId = Outside.service(this,"gus06.data.generate.string.random.number8");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String type = (String) obj;
		Map map = (Map) buildMap.t(new T1());
		
		map.put(K_TYPE,type);
		map.put(K_ID,generateId.g());
		
		return map;
	}
	
	
	
	private class T1 implements T
	{
		public Object t(Object obj) throws Exception
		{
			Map map = (Map) obj;
			
			String type = getString(map,K_TYPE);
			String name = getString(map,K_NAME);
			String value = getString(map,K_VALUE);
		
			if(type.equals(T_ROOT)) return "root";
			if(type.equals(T_TEXT)) return "text["+format(value)+"]";
			if(type.equals(T_ELEMENT)) return "element:"+name+"["+format(value)+"]";
		
			return "?"+type;
		}
		
		private String getString(Map map, String key)
		{
			if(!map.containsKey(key)) return "null";
			return (String) map.get(key);
		}
		
		private String format(String s)
		{
			s = s.replace("\n","\\n").replace("\t","\\t");
			if(s.length()<=TRUNC_LIMIT) return s;
			return s.substring(0,TRUNC_LIMIT)+"...";
		}
	}
}
