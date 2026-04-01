package a.entity.gus06.string.transform.line.frequency_i;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200205";}
	
	public static final String DELIM = "\n";
	public static final String DELIM2 = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Map map = new HashMap();
		for(int i=0;i<n.length;i++)
		increase(map,n[i]);
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys,new Comparator(){
			public int compare(Object o1, Object o2)
			{
				Integer n1 = (Integer) map.get(o1);
				Integer n2 = (Integer) map.get(o2);
				return n2.compareTo(n1);
			}
		});
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Integer nbOcc = (Integer) map.get(key);
			b.append(nbOcc.intValue()+DELIM2+key+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void increase(Map map, String key)
	{
		key = key.toLowerCase();
		if(!map.containsKey(key))
		{map.put(key,Integer.valueOf(1));return;}
		
		Integer n = (Integer)map.get(key);
		map.put(key,Integer.valueOf(n.intValue()+1));
	}
}
