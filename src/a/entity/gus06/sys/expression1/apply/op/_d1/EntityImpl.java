package a.entity.gus06.sys.expression1.apply.op._d1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161119";}
	

	private Service desc;
	private Service now;
		
	public EntityImpl() throws Exception
	{
		desc = Outside.service(this,"gus06.tostring.desc");
		now = Outside.service(this,"gus06.time.now");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		System.out.println(now.g()+":"+desc.t(obj));
		return obj;
	}
}
