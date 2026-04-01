package a.entity.gus06.sys.expression1.apply.op._split_st;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160823";}
	
	public final static String REGEX = "[ \t]+";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.splitreg");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(new Object[]{obj,REGEX});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
