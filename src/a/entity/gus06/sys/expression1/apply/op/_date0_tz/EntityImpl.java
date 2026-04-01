package a.entity.gus06.sys.expression1.apply.op._date0_tz;

import a.framework.*;
import java.util.Date;
import java.util.TimeZone;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170413";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return date0((String) obj);
		if(obj instanceof TimeZone) return date0((TimeZone) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Date date0(String id)
	{
		TimeZone tz = TimeZone.getTimeZone(id);
		return date0(tz);
	}
	
	
	private Date date0(TimeZone tz1)
	{	
		TimeZone tz0 = TimeZone.getDefault();
		long t = System.currentTimeMillis();
		
		int dt0 = tz0.getOffset(t);
		int dt1 = tz1.getOffset(t);
		
		return new Date(t-dt0+dt1);
	}
}
