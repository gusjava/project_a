package a.entity.gus06.convert.colortostring.html;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160521";}
	
	
	public Object t(Object obj) throws Exception
	{return colorToHtml((Color) obj);}

    
	private String colorToHtml(Color c) throws Exception
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return "#"+hexa(r)+hexa(g)+hexa(b);
	}
	
	private String hexa(int n)
	{
		String s = Integer.toHexString(n).toUpperCase();;
		return s.length()==1 ? "0"+s : s;
	}
}
