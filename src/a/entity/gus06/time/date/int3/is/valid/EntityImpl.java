package a.entity.gus06.time.date.int3.is.valid;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160616";}

	
	public boolean f(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length!=3) throw new Exception("Invalid int array length: "+n.length);
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR,year(n));
		c.set(Calendar.MONTH,month(n));
		c.set(Calendar.DAY_OF_MONTH,day(n)); 
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day==day(n);
	}
	
	
	private int year(int[] n)
	{return n[0];}
	
	private int month(int[] n)
	{return n[1]-1;}
	
	private int day(int[] n)
	{return n[2];}
}
