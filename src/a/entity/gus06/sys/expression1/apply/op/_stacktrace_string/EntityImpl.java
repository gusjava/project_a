package a.entity.gus06.sys.expression1.apply.op._stacktrace_string;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.tostring.stacktrace");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof StackTraceElement[]) return build.t(obj);
		if(obj instanceof Exception) return build.t(obj);
		if(obj instanceof Thread) return build.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
