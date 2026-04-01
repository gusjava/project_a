package a.entity.gus06.time.date.previous.day;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151116";}
	
	public static final long LAPSE = 86400000L;
	
	
	public Object t(Object obj) throws Exception
	{
		Date d = (Date) obj;
		return new Date(d.getTime() - LAPSE);
	}
}
