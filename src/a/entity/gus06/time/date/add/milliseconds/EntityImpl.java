package a.entity.gus06.time.date.add.milliseconds;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200513";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Date d = (Date) o[0];
		Integer n = (Integer) o[1];
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MILLISECOND,n.intValue());
		
		return calendar.getTime();
	}
}
