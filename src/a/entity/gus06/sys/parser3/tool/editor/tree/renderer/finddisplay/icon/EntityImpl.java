package a.entity.gus06.sys.parser3.tool.editor.tree.renderer.finddisplay.icon;

import a.framework.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Set;

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
	
	
	
	public Object t(Object obj) throws Exception
	{return handle(obj);}
	
	
	private String handle(Object obj)
	{
		if(obj instanceof Map)		return handleMap((Map) obj);
		if(obj instanceof Map.Entry)	return handleEntry((Map.Entry) obj);
		if(obj instanceof List)		return handleList((List) obj);
		if(obj instanceof Set)		return handleSet((Set) obj);
		if(obj instanceof Object[])	return handleArray((Object[]) obj);
		if(obj instanceof String)	return handleString((String) obj);
		if(obj instanceof Integer)	return handleInteger((Integer) obj);
		if(obj instanceof Long)		return handleLong((Long) obj);
		if(obj instanceof Double)	return handleDouble((Double) obj);
		if(obj instanceof Boolean)	return handleBoolean((Boolean) obj);
		
		return null;
	}
	
	private String handleMap(Map map)
	{
		String type = (String) get(map, TYPE);
		if(type!=null)
		{
			if(type.equals(TYPE_ELEMENT)) return "GUSEXP_element";
			if(type.equals(TYPE_SYMBOL)) return "GUSEXP_symbol";
			if(type.equals(TYPE_OTHER)) return "GUSEXP_other";
			if(type.equals(TYPE_STRING)) return "GUSEXP_string";
			if(type.equals(TYPE_DOUBLE)) return "GUSEXP_double";
			if(type.equals(TYPE_INT)) return "GUSEXP_int";
			if(type.equals(TYPE_GROUP1)) return "GUSEXP_group1";
			if(type.equals(TYPE_GROUP2)) return "GUSEXP_group2";
			if(type.equals(TYPE_GROUP3)) return "GUSEXP_group3";
			return "GUSEXP_node";
		}
		return "DATA_map";
	}
	
	private String handleEntry(Map.Entry entry)
	{
		String valueIcon = handle(entry.getValue());
		if(valueIcon==null) return null;
		return valueIcon+"_entry";
	}
	
	
	
	private String handleList(List list)
	{
		return "DATA_list";
	}
	
	private String handleSet(Set set)
	{
		return "DATA_set";
	}
	
	private String handleArray(Object[] a)
	{
		return "DATA_array";
	}
	
	private String handleString(String s)
	{
		return "DATA_string";
	}
	
	private String handleInteger(Integer n)
	{
		return "DATA_integer";
	}
	
	private String handleLong(Long l)
	{
		return "DATA_long";
	}
	
	private String handleDouble(Double d)
	{
		return "DATA_double";
	}
	
	private String handleBoolean(Boolean b)
	{
		return "DATA_boolean";
	}
	
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
