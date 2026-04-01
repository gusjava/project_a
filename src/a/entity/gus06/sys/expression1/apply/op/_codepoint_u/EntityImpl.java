package a.entity.gus06.sys.expression1.apply.op._codepoint_u;

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
			return codePoint((String) obj);
		
		if(obj instanceof Integer)
			return codePoint(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String codePoint(String s)
	{
		if(s.equals("")) return "";
		
		char[] c = s.toCharArray();
		StringBuffer b = new StringBuffer();
		for(int i=0;i<c.length;i++)
		{
			int code = Character.codePointAt(c,i);
			b.append("\\u"+hexa(code));
		}
		return b.toString();
	}
	
	private String hexa(int n)
	{
		String v = ""+Integer.toHexString(n);
		while(v.length()<4) v="0"+v;
		return v;
	}
}
