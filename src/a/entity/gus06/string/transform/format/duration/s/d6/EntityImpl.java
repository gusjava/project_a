package a.entity.gus06.string.transform.format.duration.s.d6;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}
	
	
	public static final long MIN = 60;
	public static final long HOUR = 3600;
	
	
	public Object t(Object obj) throws Exception
	{
		long duration = toLong(obj);
		
		int hours = (int) (duration/HOUR);
		duration = duration%HOUR;
		
		int min = (int) (duration/MIN);
		duration = duration%MIN;
		
		int sec = (int) duration;
		
		return format2(hours)+":"+format2(min)+":"+format2(sec);
	}
	
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof Integer) return ((Integer) obj).longValue();
		if(obj instanceof String) return Long.parseLong((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String format2(int n)
	{return n<10 ? "0"+n:""+n;}
}