package a.entity.gus06.time.date.int3.dayofweek;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160616";}
	
	/*
	0: monday
	1: tuesday
	2: wednesday
	3: thursday
	4: friday
	5: saturday
	6: sunday
	*/
	
	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length!=3) throw new Exception("Invalid int array length: "+n.length);
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR,year(n));
		calendar.set(Calendar.MONTH,month(n));
		calendar.set(Calendar.DAY_OF_MONTH,day(n));
		
		int c = (calendar.get(Calendar.DAY_OF_WEEK)+5)%7;
		return Integer.valueOf(c);
	}
	
	
	private int year(int[] n)
	{return n[0];}
	
	private int month(int[] n)
	{return n[1]-1;}
	
	private int day(int[] n)
	{return n[2];}
}
