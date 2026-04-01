package a.entity.gus06.sys.expression1.apply.op._duration_s_app;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170329";}

	public static final String T = "constant";


	private Service duration;
	private Date date;
		
	public EntityImpl() throws Exception
	{
		duration = Outside.service(this,"gus06.time.duration.between.s");
		date = (Date) Outside.resource(this,"launch.date");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return duration.t(date);
	}
}
