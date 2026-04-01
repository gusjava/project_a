package a.entity.gus06.sys.editor16x16.c.encode1;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250310";}
	
	public final static String TRANSPARENT = "255-255-255-0";
	
	public Object t(Object obj) throws Exception
	{return encodeColor((Color) obj);}
	
	private String encodeColor(Color color)
	{
		if(color==null) return null;
		int a = color.getAlpha();
		if(a==0) return TRANSPARENT;
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		return r+"-"+g+"-"+b+"-"+a;
	}
}