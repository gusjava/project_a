package a.entity.gus06.file.convert.json.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	

	public Object t(Object obj) throws Exception
	{return computeValue(obj);}
	
	
	
	private String computeValue(Object value) throws Exception
	{
		if(value==null)			return "null";
		if(value instanceof Map)	return computeMap((Map) value);
		if(value instanceof List)	return computeList((List) value);
		if(value instanceof Set)	return computeSet((Set) value);
		if(value instanceof Object[])	return computeArray((Object[]) value);
		if(value instanceof String)	return computeString((String) value);
		if(value instanceof Boolean)	return computeBoolean((Boolean) value);
		if(value instanceof Number)	return computeNumber((Number) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	// JSON object
	private String computeMap(Map map) throws Exception
	{
		if(map.isEmpty()) return "{}";
		
		StringBuffer b = new StringBuffer();
		b.append('{');
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			b.append(computeString(key)+":"+computeValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		b.append('}');
		return b.toString();
	}
	
	
	// JSON array
	private String computeList(List list) throws Exception
	{
		if(list.isEmpty()) return "[]";
		
		StringBuffer b = new StringBuffer();
		b.append('[');
		
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			b.append(computeValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		b.append(']');
		return b.toString();
	}
	
	
	// JSON array
	private String computeSet(Set set) throws Exception
	{
		if(set.isEmpty()) return "[]";
		
		StringBuffer b = new StringBuffer();
		b.append('[');
		
		for(Object value : set)
		{
			b.append(computeValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		b.append(']');
		return b.toString();
	}
	
	
	// JSON array
	private String computeArray(Object[] array) throws Exception
	{
		if(array.length==0) return "[]";
		
		StringBuffer b = new StringBuffer();
		b.append('[');
		
		for(int i=0;i<array.length;i++)
		{
			Object value = array[i];
			b.append(computeValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		b.append(']');
		return b.toString();
	}
	
	
	private String computeString(String s)
	{
		StringBuffer b = new StringBuffer();
		b.append('"');
		
		for(int i=0;i<s.length();i++)
		{
			int x = s.codePointAt(i);
			if(x<=8 || x==11 || (x>=14&&x<=31) || x>=127)
			{
				String hexa = Integer.toHexString(x);
				while(hexa.length()<4)hexa="0"+hexa;
				b.append("\\u"+hexa);
			}
			else
			{
				char c = s.charAt(i);
				switch(c)
				{
				case '"': b.append("\\\"");break;
				case '\\': b.append("\\\\");break;
				case '/': b.append("\\/");break;
				case '\b': b.append("\\b");break;
				case '\f': b.append("\\f");break;
				case '\n': b.append("\\n");break;
				case '\r': b.append("\\r");break;
				case '\t': b.append("\\t");break;
				default:b.append(c);break;
				}
			}
		}
		
		b.append('"');
		return b.toString();
	}
	
	private String computeBoolean(Boolean v)
	{
		return v.booleanValue()?"true":"false";
	}
	
	private String computeNumber(Number n)
	{
		return n.toString();
	}
}
