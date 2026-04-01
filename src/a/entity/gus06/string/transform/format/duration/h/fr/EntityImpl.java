package a.entity.gus06.string.transform.format.duration.h.fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}
	
	
	public static final long DAY = 24;
	
	public static final String U_d = "J";
	public static final String U_h = "H";
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		long duration = toLong(obj);
		
		StringBuffer b = new StringBuffer();
		
		int days = (int) (duration/DAY);
		if(days>0) b.append(days+U_d+" ");
		duration = duration%DAY;
		
		int hours = (int) duration;
		if(hours>0) b.append(hours+U_h+" ");
		
		if(b.length()==0) return "0"+U_h;
		
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof Integer) return ((Integer) obj).longValue();
		if(obj instanceof String) return Long.parseLong((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}