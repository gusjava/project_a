package a.entity.gus06.time.date.next.month;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160622";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.roll(Calendar.MONTH,1);
		return c.getTime();
	}
}
