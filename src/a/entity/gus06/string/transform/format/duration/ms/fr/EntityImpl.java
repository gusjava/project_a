package a.entity.gus06.string.transform.format.duration.ms.fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150920";}

	public static final String U_ms = "ms";

	private Service format;
	
	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.string.transform.format.duration.s.fr");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		long duration = toLong(obj);
		if(duration<1000) return duration+U_ms;
		
		long duration_s = duration/1000;
		return format.t(duration_s);
	}
	
	
	private Long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return (Long) obj;
		if(obj instanceof Integer) return Long.valueOf(((Integer) obj).longValue());
		if(obj instanceof String) return Long.valueOf((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
