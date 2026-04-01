package a.entity.gus06.time.date.previous.year;

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
//		c.roll(Calendar.YEAR,-1); roll fait la m�me chose pour YEAR ?
		c.add(Calendar.YEAR,-1);
		return c.getTime();
	}
}