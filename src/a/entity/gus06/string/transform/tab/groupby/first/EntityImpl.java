package a.entity.gus06.string.transform.tab.groupby.first;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190123";}
	
	public static final String DELIM = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		Map map = new HashMap();
		List keys = new ArrayList();
		
		for(int i=0;i<n.length;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			if(parts.length>1)
			{
				String key = parts[0];
				if(!map.containsKey(key))
				{
					map.put(key,new ArrayList());
					keys.add(key);
				}
				
				List list = (List) map.get(key);
				for(int j=0;j<parts.length-1;j++)
				{
					String value = parts[j+1];
					if(list.size()==j) list.add(new HashSet());
					Set set = (Set) list.get(j);
					set.add(value);
				}
			}
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			List list = (List) map.get(key);
			
			b.append(key);
			for(int j=0;j<list.size();j++)
			{
				b.append(DELIM);
				Set set = (Set) list.get(j);
				appendSet(b,set);
			}
			b.append("\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void appendSet(StringBuffer b, Set set)
	{
		List list = new ArrayList(set);
		Collections.sort(list);
		int nb = list.size();
		for(int i=0;i<nb;i++)
		{
			String v = (String) list.get(i);
			b.append(v);
			if(i<nb-1) b.append(";");
		}
	}
}
