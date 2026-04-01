package a.entity.gus06.time.timestamp.yyyymmdd.buildrange;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}
	
	public static final int LOOP_LIMIT = 100000;
	

	private Service dayNb;

	public EntityImpl() throws Exception
	{
		dayNb = Outside.service(this,"gus06.data.time.days.count.yyyymm");
	}
	
	
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
		
		String t = t1;
		
		int year = Integer.parseInt(t.substring(0,4));
		int month = Integer.parseInt(t.substring(4,6));
		int day = Integer.parseInt(t.substring(6,8));
		
		int monthMax = (int) dayNb.t(new int[]{year,month});
		
		int c = 0;
		while(!t.equals(t2))
		{
			c++;
			if(c>=LOOP_LIMIT) throw new Exception("Loop limit has been reached: "+LOOP_LIMIT);
			
			day++;
			if(day>monthMax)
			{
				day=1;
				month++;
				if(month==13)
				{
					month = 1;
					year++;
				}
				monthMax = (int) dayNb.t(new int[]{year,month});
			}
			
			t = year+format(month)+format(day);
			list1.add(t);
		}
		
		return list1;
	}
	
	
	private String format(int v)
	{return v<10?"0"+v:""+v;}
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set) return new ArrayList((Set) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
