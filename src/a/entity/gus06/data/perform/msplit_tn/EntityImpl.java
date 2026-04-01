package a.entity.gus06.data.perform.msplit_tn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220222";}
	
	public static final String GLUE1 = "\t";
	public static final String GLUE2 = "\n";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.msplit");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return perform.t(new Object[]{obj,GLUE1,GLUE2});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}