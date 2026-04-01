package a.entity.gus06.time.timestamp.yyyy.buildrange;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}
	
	public static final int LOOP_LIMIT = 100000;
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = toList(obj);
		if(list.isEmpty()) return new ArrayList();
		if(list.size()==1) return new ArrayList(list);
		
		Collections.sort(list);
		int nb = list.size();
		
		String t1 = (String) list.get(0);
		String t2 = (String) list.get(nb-1);
		
		List list1 = new ArrayList();
		list1.add(t1);
		
		int year1 = Integer.parseInt(t1);
		int year2 = Integer.parseInt(t2);
		
		int c = 0;
		while(year1<year2)
		{
			c++;
			if(c>=LOOP_LIMIT) throw new Exception("Loop limit has been reached: "+LOOP_LIMIT);
			
			year1++;
			list1.add(""+year1);
		}
		return list1;
	}
	
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set) return new ArrayList((Set) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
