package a.entity.gus06.sys.expression1.apply.op._q1es;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200109";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Boolean) return "\""+obj+"\"";
		if(obj instanceof Number) return "\""+obj+"\"";
		if(obj instanceof String) return "\""+format((String) obj)+"\"";
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String format(String s)
	{return s.replace("\\","\\\\").replace("\"","\\\"");}
}
