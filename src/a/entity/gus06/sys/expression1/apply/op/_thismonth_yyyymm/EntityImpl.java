package a.entity.gus06.sys.expression1.apply.op._thismonth_yyyymm;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161120";}

	public static final String T = "constant";
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return sdf.format(new Date());
	}
}
