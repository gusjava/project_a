package a.entity.gus06.find.stringlist.sorted;

import a.framework.*;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150501";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		List list = findList(obj);
		Collections.sort(list);
		return list;
	}
	
	
	private List findList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof String[]) return Arrays.asList((String[])obj);
		if(obj instanceof Collection) return new ArrayList((Collection) obj);
		if(obj instanceof Map) return mapToList((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List mapToList(Map map)
	{return new ArrayList(map.keySet());}
}
