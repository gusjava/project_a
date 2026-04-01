package a.entity.gus06.sys.expression1.apply.op._qr2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return format((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String format(String s)
	{
		if(s.startsWith("'") && s.endsWith("'"))
			return s.substring(1,s.length()-1);
		return s;
	}
}
