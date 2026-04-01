package a.entity.gus06.sys.expression1.apply.op._hexa_toint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return perform((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object perform(String s)
	{
		try{return Integer.valueOf(Integer.parseInt(s,16));}
		catch(NumberFormatException e){}
		
		return Long.valueOf(Long.parseLong(s,16));
	}
}
