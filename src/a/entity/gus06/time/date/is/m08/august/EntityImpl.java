package a.entity.gus06.time.date.is.m08.august;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160801";}
	

	
	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.MONTH)==Calendar.AUGUST;
	}
}
