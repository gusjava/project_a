package a.entity.gus06.convert.colortostring.rgb;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180226";}
	
	
	public Object t(Object obj) throws Exception
	{return colorToRgb((Color) obj);}

    
	private String colorToRgb(Color c) throws Exception
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return r+"-"+g+"-"+b;
	}
}

