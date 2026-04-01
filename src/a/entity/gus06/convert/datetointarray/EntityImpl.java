package a.entity.gus06.convert.datetointarray;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}

	
	public Object t(Object obj) throws Exception
	{return dateToIntArray((Date) obj);}
	
	
	private int[] dateToIntArray(Date date) throws Exception
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millisecond = c.get(Calendar.MILLISECOND);
		
		return new int[]{year,month,day,hour,minute,second,millisecond};
	}
}
