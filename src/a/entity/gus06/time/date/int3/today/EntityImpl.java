package a.entity.gus06.time.date.int3.today;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160617";}
	

	
	public Object g() throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return new int[]{year,month,day};
	}
}
