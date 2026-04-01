package a.entity.gus06.tostring.map1;

import java.util.Map;
import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}



	public Object t(Object obj) throws Exception
	{return mapToString((Map) obj);}
	
	
	
	private String mapToString(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(map.keySet());
		Collections.sort(list);
		
		for(int i=0;i<list.size();i++)
		{
			Object key = list.get(i);
			Object value = map.get(key);
			
			b.append(key+"="+value+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
