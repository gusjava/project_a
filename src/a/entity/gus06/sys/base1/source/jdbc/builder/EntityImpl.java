package a.entity.gus06.sys.base1.source.jdbc.builder;

import a.framework.*;
import java.util.HashMap;
import java.sql.Connection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150808";}

	public static final String KEY_TYPE = "type";
	public static final String KEY_CX = "cx";
	public static final String KEY_PATH = "path";
	public static final String KEY_STRUCTURE = "structure";


	private Service buildCxHolder;

	public EntityImpl() throws Exception
	{
		buildCxHolder = Outside.service(this,"gus06.jdbc.connection.holder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Source(obj);
	}
	
	
	private class Source implements G
	{
		private Object infos;
		private G cxHolder;
		
		public Source(Object infos) throws Exception
		{
			this.infos = infos;
			cxHolder = (G) buildCxHolder.t(infos);
		}
		
		public Object g() throws Exception
		{
			Connection cx = (Connection) cxHolder.g();
			String path = find(infos,KEY_PATH,3);
			String structure = find(infos,KEY_STRUCTURE,4);
			
			Map map = new HashMap();
			
			map.put(KEY_TYPE,"jdbc");
			map.put(KEY_CX,cx);
			map.put(KEY_PATH,path);
			map.put(KEY_STRUCTURE,structure);
			
			return map;
		}
	}
	
	
	
	
	
	
		
	private String find(Object o, String key, int index) throws Exception
	{
		if(o instanceof G) o = ((G)o).g();
		
		if(o instanceof Map) return get1((Map)o,key);
		if(o instanceof String[]) return get1((String[])o,index);
		throw new Exception("Invalid data type: "+o.getClass().getName());
	}
	
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	private String get1(String[] array, int index) throws Exception
	{
		if(array.length <= index) throw new Exception("Element not found at index: "+index);
		return array[index];
	}
}
