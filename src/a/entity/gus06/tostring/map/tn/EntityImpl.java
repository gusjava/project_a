package a.entity.gus06.tostring.map.tn;

import java.util.Map;
import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201014";}



	public Object t(Object obj) throws Exception
	{return mapToString((Map) obj);}
	
	
	
	private String mapToString(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(map.keySet());
		Collections.sort(list);
		
		for(int i=0;i<list.size();i++)
		{
			String key = ""+list.get(i);
			String value = ""+map.get(key);
			
			if(key.contains("\t")) throw new Exception("Invalid key: "+key);
			if(key.contains("\n")) throw new Exception("Invalid key: "+key);
			if(value.contains("\n")) throw new Exception("Invalid value: "+value);
			
			b.append(key+"\t"+value+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
