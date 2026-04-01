package a.entity.gus06.time.year.check.leapyear;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191208";}


	private Service findYear;

	public EntityImpl() throws Exception
	{
		findYear = Outside.service(this,"gus06.time.year.find");
	}
	
	public boolean f(Object obj) throws Exception
	{
		int year = ((Integer) findYear.t(obj)).intValue();
		
		if(year%400==0) return true;
		if(year%100==0) return false;
		return year%4==0;
	}
}
