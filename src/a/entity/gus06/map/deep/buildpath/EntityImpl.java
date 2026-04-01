package a.entity.gus06.map.deep.buildpath;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220210";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof List) return listToPath((List) obj);
		if(obj instanceof Object[]) return arrayToPath((Object[]) obj);
		if(obj instanceof String) return stringToPath((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] listToPath(List list)
	{
		String[] path = new String[list.size()];
		for(int i=0;i<list.size();i++) path[i] = (String) list.get(i);
		return path;
	}
	
	private String[] arrayToPath(Object[] array)
	{
		String[] path = new String[array.length];
		for(int i=0;i<array.length;i++) path[i] = (String) array[i];
		return path;
	}
	
	private String[] stringToPath(String s)
	{
		return s.split("\\.");
	}
}