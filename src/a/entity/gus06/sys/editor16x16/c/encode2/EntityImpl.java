package a.entity.gus06.sys.editor16x16.c.encode2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250311";}
	
	public final static String TRANSPARENT = "255-255-255-0";
	
	public Object t(Object obj) throws Exception
	{return encodeColor((int[]) obj);}
	
	private String encodeColor(int[] c)
	{
		if(c==null) return null;
		
		int r = c[0];
		int g = c[1];
		int b = c[2];
		int a = c[3];
		
		if(a==0) return TRANSPARENT;
		return r+"-"+g+"-"+b+"-"+a;
	}
}