package a.entity.gus06.swing.tree.cust.renderer.obj1.finddisplay.text;

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


	private Service formatDate;
	
	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.en.format1");
	}
	
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
		
		String className = obj.getClass().getName();
		if(className.startsWith("java.lang.")) return className.substring(10);
		return className;
	}
	
	
	private String handleMap(Map map)
	{
		return "{"+map.size()+"}";
	}
	
	private String handleList(List list)
	{
		return "["+list.size()+"]";
	}
	
	private String handleArray(Object[] array)
	{
		return "["+array.length+"]";
	}
	
	private String handleSet(Set set)
	{
		return "("+set.size()+")";
	}
	
	private String handleString(String s)
	{
		s = s.trim();
		if(s.length()>15) return s.substring(15)+"...";
		return s;
	}
	
	private String handleFile(File f)
	{
		return f.getName();
	}
	
	private String handleDate(Date d) throws Exception
	{
		return (String) formatDate.t(d);
	}
	
	private String handleURL(URL url) throws Exception
	{
		return url.toString();
	}
	
	private String handleClass(Class cl) throws Exception
	{
		String className = cl.getName();
		if(className.startsWith("java.lang."))
			return className.substring(10);
		return className;
	}
	
	private String handleInteger(Integer n)
	{
		return ""+n;
	}
	
	private String handleLong(Long n)
	{
		return ""+n;
	}
	
	private String handleDouble(Double n)
	{
		return ""+n;
	}
	
	private String handleBoolean(Boolean b)
	{
		return ""+b;
	}
	
	private String handleEntry(Map.Entry entry) throws Exception
	{
		String key = (String) t(entry.getKey());
		String value = (String) t(entry.getValue());
		return key+":"+value;
	}
}