package a.entity.gus06.sys.dirsearch1.tool.terms.colorarray;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20200124";}
	
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	public static final Color[] ARRAY = new Color[]{
			new Color(128,160,255),
			new Color(140,216,191),
			new Color(255,204,0),
			new Color(228,161,228),
			new Color(235,160,160)};

	
	public Object g() throws Exception
	{return ARRAY;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		int index = toInt(obj);
		return ARRAY.length>index ? ARRAY[index] : DEFAULT_COLOR;
	}
	
	private int toInt(Object obj) throws Exception
	{return Integer.parseInt(""+obj);}
}
