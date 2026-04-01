package a.entity.gus06.time.date.month.en;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}
	
	
	private Service find;
	private String[] months;
	
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.data.time.months.name_en");
		months = (String[]) find.g();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.MONTH);
		
		return months[index];
	}
}
