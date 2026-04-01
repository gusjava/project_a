package a.entity.gus06.sys.expression1.apply.op._tochars;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160203";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return toChars((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] toChars(String s)
	{
		int len = s.length();
		String[] r = new String[len];
		for(int i=0;i<len;i++) r[i] = ""+s.charAt(i);
		return r;
	}
}
