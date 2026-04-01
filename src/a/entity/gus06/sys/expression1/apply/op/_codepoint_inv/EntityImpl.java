package a.entity.gus06.sys.expression1.apply.op._codepoint_inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String)
			return codePointInv((String) obj);
		
		if(obj instanceof Integer)
			return codePointInv(((Integer)obj).intValue());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String codePointInv(int code)
	{
		return new String(Character.toChars(code));
	}
	
	
	private String codePointInv(String s)
	{
		if(s.startsWith("\\u")) s = s.substring(2);
		int code = Integer.parseInt(s, 16);
		return new String(Character.toChars(code));
	}
}
