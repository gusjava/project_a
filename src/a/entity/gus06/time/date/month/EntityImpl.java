package a.entity.gus06.time.date.month;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}
	
	/*
	1: january
	2: febuary
	3: march
	4: april
	5: may
	6: june
	7: july
	8: august
	9: september
	10: october
	11: november
	12: december
	*/
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int c = calendar.get(Calendar.MONTH)+1;
		
		return Integer.valueOf(c);
	}
}
