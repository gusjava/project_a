package a.entity.gus06.convert.intarraytodate;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}

	
	public Object t(Object obj) throws Exception
	{return intArrayToDate((int[]) obj);}
	
	
	private Date intArrayToDate(int[] n) throws Exception
	{
		if(n.length<1 || n.length>7) throw new Exception("Invalid int array length: "+n.length);
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR,year(n));
		c.set(Calendar.MONTH,month(n));
		c.set(Calendar.DAY_OF_MONTH,day(n)); 
		c.set(Calendar.HOUR_OF_DAY,hour(n)); 
		c.set(Calendar.MINUTE,minute(n)); 
		c.set(Calendar.SECOND,second(n)); 
		c.set(Calendar.MILLISECOND,millisecond(n)); 
		
		return c.getTime();
	}
	
	
	private int year(int[] n)
	{return n[0];}
	
	private int month(int[] n)
	{return n.length>1?n[1]-1:0;}
	
	private int day(int[] n)
	{return n.length>2?n[2]:1;}
	
	private int hour(int[] n)
	{return n.length>3?n[3]:0;}
	
	private int minute(int[] n)
	{return n.length>4?n[4]:0;}
	
	private int second(int[] n)
	{return n.length>5?n[5]:0;}
	
	private int millisecond(int[] n)
	{return n.length>6?n[6]:0;}
}
