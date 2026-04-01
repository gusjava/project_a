package a.entity.gus06.time.date.dayofmonth;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}
	
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int c = calendar.get(Calendar.DAY_OF_MONTH);
		
		return Integer.valueOf(c);
	}
}
