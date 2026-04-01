package a.entity.gus06.time.date.previous.hour;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160622";}
	
	public static final long LAPSE = 3600000L;
	
	
	public Object t(Object obj) throws Exception
	{
		Date d = (Date) obj;
		return new Date(d.getTime() - LAPSE);
	}
}
