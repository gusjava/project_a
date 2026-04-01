package a.entity.gus06.sys.store.t.string.string.customizer1;

import a.framework.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}


	private Service findObj;


	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return formatString((String) obj);
		if(obj instanceof Map)
			return formatMap((Map) obj);
		if(obj instanceof Set)
			return formatSet((Set) obj);
		if(obj instanceof List)
			return formatList((List) obj);
		if(obj instanceof String[])
			return formatArray((String[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	
	private Map formatMap(Map map) throws Exception
	{
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			
			String key1 = formatString(key);
			String value1 = formatString(value);
			
			if(map1.containsKey(key1))
				throw new Exception("Key already found inside new map: "+key1);
			map1.put(key1,value1);
		}
		return map1;
	}
	
	
	
	
	private Set formatSet(Set set) throws Exception
	{
		Set set1 = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			String value1 = formatString(value);
			set1.add(value1);
		}
		return set1;
	}
	
	
	
	
	private List formatList(List list) throws Exception
	{
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			String value = (String) list.get(i);
			String value1 = formatString(value);
			list1.add(value1);
		}
		return list1;
	}
	
	
	
	
	private String[] formatArray(String[] array) throws Exception
	{
		String[] array1 = new String[array.length];
		for(int i=0;i<array.length;i++)
			array1[i] = formatString(array[i]);
		return array1;
	}
	
	
	

	
	
	
	
	
	public static final Pattern P_TAG = Pattern.compile("<[^>]+>");
	
	
	private String formatString(String s) throws Exception
	{
		Matcher m = P_TAG.matcher(s);
		while(m.find())
		{
			String tag = m.group();
			String tag_content = tag.substring(1,tag.length()-1);
			String tag_value = resolveTag(tag_content);
			
			s = s.replace(tag,tag_value);
		}
		return s;
	}
	

	
	
	
	private String resolveTag(String rule) throws Exception
	{
		if(rule.startsWith("!")) return "<"+rule.substring(1)+">";
		if(!rule.contains(":")) throw new Exception("Invalid obj rule: "+rule);
		if(!rule.startsWith("string:") && !rule.startsWith("string;")) rule = "string;"+rule;
		return (String) findObj.t(rule);
	}
}

