package a.entity.gus06.tostring.maplinks;

import java.util.Map;
import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190516";}

	// CODE A REVOIR !!!!

	public Object t(Object obj) throws Exception
	{return mapToString((Map) obj);}
	
	
	
	private String mapToString(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		Set done = new HashSet();
		
		print(b,map,done,"");
		return b.toString();
	}
	
	
	private void print(StringBuffer b, Map map, Set done, String offset)
	{
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			printKey(b,map,done,offset,key);
		}
	}
	
	
	
	private void printKey(StringBuffer b, Map map, Set done, String offset, String key)
	{
		if(done.contains(key)) 
		{
			b.append(offset+key+" ...\n");
			return;
		}
		done.add(key);
		b.append(offset+key+"\n");
		
		if(map.containsKey(key))
		{
			Set set = (Set) map.get(key);
			List list = new ArrayList(set);
			Collections.sort(list);
			
			offset += "\t";
			for(int i=0;i<list.size();i++)
			{
				String v = (String) list.get(i);
				printKey(b,map,done,offset,v);
			}
		}
	}
}
