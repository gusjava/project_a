package a.entity.gus06.map.string.stringtomap.builder.ini;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}
	
	
	public Object t(Object obj) throws Exception
	{
		HashMap map = new HashMap();
		String[] nn = toArray(obj);
		if(nn==null) return map;
		
		for(String n:nn) if(!n.trim().equals(""))
		{
			String[] kk = n.split("=",2);
			if(kk.length==2) map.put(kk[0].trim(),kk[1].trim());
		}
		return map;
	}
	
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj.equals("")) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).replace("\r","").split("\n");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
