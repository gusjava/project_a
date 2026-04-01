package a.entity.gus06.sys.expression1.apply.op._wrap_gtp_e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250730";}


	private Service wrapPO;
	
	public EntityImpl() throws Exception
	{
		wrapPO = Outside.service(this,"gus06.feature.wrap.gtp.e");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Object[]) return wrapPO.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}