package a.entity.gus06.sys.expression1.apply.op._cause_last;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.exception.cause.findlast");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Exception) return find.t(obj);
		if(obj instanceof Throwable) return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
