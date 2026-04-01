package a.entity.gus06.sys.expression1.apply.op._sortnum_inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160804";}
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.sortnum.inv");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		return perform.t(obj);
	}
}
