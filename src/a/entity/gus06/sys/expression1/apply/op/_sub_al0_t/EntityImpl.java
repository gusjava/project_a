package a.entity.gus06.sys.expression1.apply.op._sub_al0_t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221012";}
	
	public final static String DELIM = "\t";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.substr.after0.last");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(new Object[]{obj,DELIM});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}