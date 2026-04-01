package a.entity.gus06.sys.parser3.tool.editor.tree.renderer.finddisplay.text;

import a.framework.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_OTHER = "other";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_INT = "int";
	
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";


	private Service formatDate;
	
	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.en.format1");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)		return handleMap((Map) obj);
		if(obj instanceof List)		return handleList((List) obj);
		if(obj instanceof String)	return handleString((String) obj);
		if(obj instanceof Map.Entry)	return handleEntry((Map.Entry) obj);
		
		String className = obj.getClass().getName();
		if(className.startsWith("java.lang.")) return className.substring(10);
		return className;
	}
	
	
	private String handleMap(Map map)
	{
		String type = (String) get(map, TYPE);
		if(type!=null)
		{
			if(type.equals(TYPE_ELEMENT)) return getValue(map);
			if(type.equals(TYPE_SYMBOL)) return getValue(map);
			if(type.equals(TYPE_OTHER)) return getValue(map);
			if(type.equals(TYPE_STRING)) return getValue(map);
			if(type.equals(TYPE_DOUBLE)) return getValue(map);
			if(type.equals(TYPE_INT)) return getValue(map);
			if(type.equals(TYPE_GROUP1)) return getValue(map);
			if(type.equals(TYPE_GROUP2)) return getValue(map);
			if(type.equals(TYPE_GROUP3)) return getValue(map);
			
			return type+":"+getValue(map);
		}
		return "{"+map.size()+"}";
	}
	
	private String handleList(List list)
	{
		return "["+list.size()+"]";
	}
	
	private String handleEntry(Map.Entry entry) throws Exception
	{
		String key = (String) t(entry.getKey());
		String value = (String) t(entry.getValue());
		return key+":"+value;
	}
	
	private String handleString(String s)
	{
		s = s.trim();
		if(s.length()>15) return s.substring(0,15)+"...";
		return s;
	}
	
	
	private String getValue(Map map)
	{
		Object value = get(map, VALUE);
		if(value instanceof List) return "["+((List) value).size()+"]";
		if(value instanceof Map) return "{"+((Map) value).size()+"}";
		return handleString(""+value);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}