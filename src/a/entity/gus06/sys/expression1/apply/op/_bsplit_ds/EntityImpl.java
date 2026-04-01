package a.entity.gus06.sys.expression1.apply.op._bsplit_ds;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}
	
	public static final String GLUE1 = ":";
	public static final String GLUE2 = " ";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.bsplit");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(new Object[]{obj,GLUE1,GLUE2});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
