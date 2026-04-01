package a.entity.gus06.data.time.days.count.yyyymm;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service checkLeapYear;
	private Service findIntArray;


	public EntityImpl() throws Exception
	{
		checkLeapYear = Outside.service(this,"gus06.time.year.check.leapyear");
		findIntArray = Outside.service(this,"gus06.find.intarray.len2.month");
	}
	
	public Object t(Object obj) throws Exception
	{
		int[] yyyymm = (int[]) findIntArray.t(obj);
		int year = yyyymm[0];
		int month = yyyymm[1];
		
		switch(month)
		{
			case 1:return 31;
			case 2:return checkLeapYear.f(year) ? 29 : 28;
			case 3:return 31;
			case 4:return 30;
			case 5:return 31;
			case 6:return 30;
			case 7:return 31;
			case 8:return 31;
			case 9:return 30;
			case 10:return 31;
			case 11:return 30;
			case 12:return 31;
			
			default:throw new Exception("Invalid month value: "+month);
		}
	}
}
