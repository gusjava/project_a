package a.entity.gus06.sys.expression1.apply.op._thismonth_fr;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161120";}

	public static final String T = "constant";


	private Service month;
	
	public EntityImpl() throws Exception
	{
		month = Outside.service(this,"gus06.time.date.month.fr");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return month.t(new Date());
	}
}
