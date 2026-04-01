package a.entity.gus06.time.date.dayofweek.ja;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}
	
	
	private Service dayOfWeek;
	private Service find;
	
	private String[] days;
	
	
	public EntityImpl() throws Exception
	{
		dayOfWeek = Outside.service(this,"gus06.time.date.dayofweek");
		find = Outside.service(this,"gus06.data.time.days.name_ja");
		
		days = (String[]) find.g();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Integer n = (Integer) dayOfWeek.t(obj);
		return days[n.intValue()];
	}
}
