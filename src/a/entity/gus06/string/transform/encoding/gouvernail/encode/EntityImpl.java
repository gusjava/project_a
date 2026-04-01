package a.entity.gus06.string.transform.encoding.gouvernail.encode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}

	private final static String GOUVERNAIL = "GOUVERNAIL";
	private final static String _0123456789 = "0123456789";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int number = Integer.parseInt(s);
		if(number<0) throw new Exception("Invalid input: "+s);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		b.append(encode(s.charAt(i)));
		
		return b.toString();
	}
	
	
	
	private char encode(char c) throws Exception
	{
		int n = _0123456789.indexOf((int)c);
		if(n<0) throw new Exception("Invalid character: "+c);
		return GOUVERNAIL.charAt(n);
	}
}
