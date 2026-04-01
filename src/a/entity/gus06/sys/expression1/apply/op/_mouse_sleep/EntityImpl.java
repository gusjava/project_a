package a.entity.gus06.sys.expression1.apply.op._mouse_sleep;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161011";}

	public static final String T = "constant";


	private Service activity;
	
	public EntityImpl() throws Exception
	{activity = Outside.service(this,"gus06.watching.mouse.activity");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return Boolean.valueOf(!activity.f(null));
	}
}
