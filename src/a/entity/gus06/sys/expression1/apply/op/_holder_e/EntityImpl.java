package a.entity.gus06.sys.expression1.apply.op._holder_e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170805";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.buildholder.execute");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof E) return perform.t(obj);
		return perform.g();
	}
}
