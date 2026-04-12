package a.entity.gus06.convert.stringtomap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140911";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String delim = findDelim(s);
		String[] lines = s.split("\n");
		
		Map map = new HashMap();
		
		for(int i=0;i<lines.length;i++)
		if(!lines[i].trim().equals(""))
		{
			String[] n = lines[i].split(delim,2);
			if(n.length!=2) throw new Exception("Invalid text: "+s);
			map.put(n[0].trim(),n[1].trim());
		}
		return map;
	}
	
	private String findDelim(String s) throws Exception
	{
		if(s.contains("=")) return "=";
		if(s.contains(":")) return ":";
		if(s.contains("\t")) return "\t";
		throw new Exception("Invalid text: "+s);
	}
}
