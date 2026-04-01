package a.entity.gus06.sys.editor16x16.c.decode2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250311";}
	
	
	public Object t(Object obj) throws Exception
	{return decodeColor((String) obj);}
	
	
	private int[] decodeColor(String c)
	{
		if(c==null) return null;
		String[] n = c.split("-");
		int r = toInt(n[0]);
		int g = toInt(n[1]);
		int b = toInt(n[2]);
		int a = toInt(n[3]);
		
		return new int[]{r,g,b,a};
	}
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
}