package a.entity.gus06.swing.tree.cust.renderer.obj1.finddisplay.icon;

import a.framework.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.util.Date;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221011";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)		return handleMap((Map) obj);
		if(obj instanceof List)		return handleList((List) obj);
		if(obj instanceof Object[])	return handleArray((Object[]) obj);
		if(obj instanceof Set)		return handleSet((Set) obj);
		if(obj instanceof String)	return handleString((String) obj);
		if(obj instanceof File)		return handleFile((File) obj);
		if(obj instanceof Date)		return handleDate((Date) obj);
		if(obj instanceof URL)		return handleURL((URL) obj);
		if(obj instanceof Class)	return handleClass((Class) obj);
		if(obj instanceof Integer)	return handleInteger((Integer) obj);
		if(obj instanceof Long)		return handleLong((Long) obj);
		if(obj instanceof Double)	return handleDouble((Double) obj);
		if(obj instanceof Boolean)	return handleBoolean((Boolean) obj);
		if(obj instanceof Map.Entry)	return handleEntry((Map.Entry) obj);
		
		return null;
	}
	
	
	private String handleMap(Map map)
	{
		return "DATA_map";
	}
	
	private String handleList(List list)
	{
		return "DATA_list";
	}
	private String handleArray(Object[] array)
	{
		return "DATA_array";
	}
	
	private String handleSet(Set set)
	{
		return "DATA_set";
	}
	
	private String handleString(String s)
	{
		return "DATA_string";
	}
	
	private String handleFile(File f)
	{
		return "DATA_file";
	}
	
	private String handleDate(Date d)
	{
		return "DATA_date";
	}
	
	private String handleURL(URL u)
	{
		return "DATA_url";
	}
	
	private String handleClass(Class cl)
	{
		return "DATA_class";
	}
	
	private String handleInteger(Integer n)
	{
		return "DATA_integer";
	}
	
	private String handleLong(Long n)
	{
		return "DATA_long";
	}
	
	private String handleDouble(Double n)
	{
		return "DATA_double";
	}
	
	private String handleBoolean(Boolean b)
	{
		return "DATA_boolean";
	}
	
	private String handleEntry(Map.Entry entry)
	{
		return "DATA_entry";
	}
}