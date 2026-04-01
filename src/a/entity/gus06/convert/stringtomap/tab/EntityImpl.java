package a.entity.gus06.convert.stringtomap.tab;

import a.framework.*;
import java.util.HashMap;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210511";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] lines = s.split("\n");
		
		HashMap map = new HashMap();
		
		for(int i=0;i<lines.length;i++)
		if(!lines[i].trim().equals(""))
		{
			String[] n = lines[i].split("\t",2);
			if(n.length!=2) throw new Exception("Invalid text: "+s);
			map.put(n[0].trim(),n[1].trim());
		}
		return map;
	}
}