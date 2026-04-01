package a.entity.gus06.time.date.is.yesterday;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160625";}

	
	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		String s = yyyyMMdd(date);
		String s0 = yyyyMMdd(yesterday());
		
		return s.equals(s0);
	}
	
	
	private Date yesterday()
	{return new Date(System.currentTimeMillis() - 86400000L);}
	
	
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	
	private String yyyyMMdd(Date date)
	{return yyyyMMdd.format(date);}
}
