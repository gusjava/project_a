package a.entity.gus06.time.duration.fromnow.year;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190529";}
	
	public static final long FACTOR = 31557600000L;

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Date) return toNow((Date) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Long toNow(Date d)
	{
		long t1 = System.currentTimeMillis();
		long dt = d.getTime()-t1;
		return Long.valueOf(dt/FACTOR);
	}
}
