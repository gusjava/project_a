package a.entity.gus06.time.date.is.today;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160614";}
	

	
	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		String s = yyyyMMdd(date);
		String s0 = yyyyMMdd(new Date());
		
		return s.equals(s0);
	}
	
	
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	
	private String yyyyMMdd(Date date)
	{return yyyyMMdd.format(date);}
}
