package a.entity.gus06.time.duration.tonow.ms;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170806";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Date) return toNow((Date) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Long toNow(Date d)
	{
		long t1 = System.currentTimeMillis();
		long dt = t1-d.getTime();
		return Long.valueOf(dt);
	}
}
