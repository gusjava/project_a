package a.entity.gus06.string.transform.str.trim.end;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}
	
	public static final String WHITE = " \t\n\r";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s==null) return null;
		if(s.equals("")) return "";
		
		int offset = findLast(s);
		return s.substring(0,offset+1);
	}
	
	private int findLast(String s)
	{
		int i = s.length()-1;
		while(WHITE.indexOf(s.charAt(i))>=0) i--;
		return i;
	}
}