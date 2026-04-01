package a.entity.gus06.string.transform.tab.order.shuffle;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	public static final String DELIM = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		String[] parts0 = n[0].split(DELIM,-1);
		int number = parts0.length;
		
		List list = orderList(number);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String[] parts = n[i].split(DELIM,-1);
			
			for(int j=0;j<number;j++)
			{
				Integer v = (Integer) list.get(j);
				b.append(parts[v.intValue()]);
				if(j<number-1) b.append(DELIM);
			}
			b.append("\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	
	private List orderList(int number)
	{
		List list = new ArrayList(number);
		for(int i=0;i<number;i++) list.add(Integer.valueOf(i));
		Collections.shuffle(list);
		return list;
	}
}
