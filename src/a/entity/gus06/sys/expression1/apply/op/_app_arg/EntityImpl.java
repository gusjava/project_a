package a.entity.gus06.sys.expression1.apply.op._app_arg;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161015";}
	

	private String[] args;
		
	public EntityImpl() throws Exception
	{
		args = (String[]) Outside.resource(this,"launch.args");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer)	return arg((Integer) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String arg(Integer n)
	{
		int index = n.intValue();
		if(index<0 || index>args.length-1) return null;
		return args[index];
	}
}
