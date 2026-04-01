package a.entity.gus06.time.date.is.thismonth;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160801";}
	

	
	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		String m = yyyyMM(date);
		String m0 = yyyyMM(new Date());
		
		return m.equals(m0);
	}
	
	
	
	private SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	
	private String yyyyMM(Date date)
	{return yyyyMM.format(date);}
}
