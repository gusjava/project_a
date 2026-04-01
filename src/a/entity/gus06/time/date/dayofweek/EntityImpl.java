package a.entity.gus06.time.date.dayofweek;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160614";}
	
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
		Date date = (Date) obj;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int c = (calendar.get(Calendar.DAY_OF_WEEK)+5)%7;
		return Integer.valueOf(c);
	}
}
