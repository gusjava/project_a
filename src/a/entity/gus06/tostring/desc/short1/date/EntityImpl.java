package a.entity.gus06.tostring.desc.short1.date;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


	private Service dateToString;
	
	
	public EntityImpl() throws Exception
	{
		dateToString = Outside.service(this,"gus06.time.date.yyyymmdd_hhmmss");
	}



	public Object t(Object obj) throws Exception
	{
		Date d = (Date) obj;
		return "Date: "+dateToString.t(d);
	}
}
