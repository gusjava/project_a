package a.entity.gus06.sys.expression1.apply.op._date0_tz_en;

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
		
		TimeZone tz0 = TimeZone.getDefault();
		TimeZone tz1 = TimeZone.getTimeZone("Europe/London");
		long t = System.currentTimeMillis();
		
		int dt0 = tz0.getOffset(t);
		int dt1 = tz1.getOffset(t);
		
		return new Date(t-dt0+dt1);
	}
}
