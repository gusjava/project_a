package a.entity.gus06.sys.expression1.apply.op._codepoint;

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
	
	
	
	private Object codePoint(String s)
	{
		if(s.equals("")) return "";
		
		char[] c = s.toCharArray();
		if(c.length==1) return Integer.valueOf(codeAt(c,0));
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<c.length;i++) b.append(codeAt(c,i)+";");
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private int codeAt(char[] c, int i)
	{return Character.codePointAt(c,i);}
}
