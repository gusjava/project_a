package a.entity.gus06.sys.expression1.apply.op._formatduration1_s_en;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.format.duration.s.en");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return perform.t(obj);
		if(obj instanceof Long) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
