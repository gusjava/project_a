package a.entity.gus06.data.time.days.count.year;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service checkLeapYear;


	public EntityImpl() throws Exception
	{
		checkLeapYear = Outside.service(this,"gus06.time.year.check.leapyear");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		boolean leap = checkLeapYear.f(obj);
		return leap ? 366 : 365;
	}
}
