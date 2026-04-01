package a.entity.gus06.sys.expression1.apply.op._bjoin_uc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}
	
	public static final String GLUE1 = "_";
	public static final String GLUE2 = ",";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.bjoin");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof int[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof long[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof boolean[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof double[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof float[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof char[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		if(obj instanceof short[][])		return perform.t(new Object[]{obj,GLUE1,GLUE2});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
