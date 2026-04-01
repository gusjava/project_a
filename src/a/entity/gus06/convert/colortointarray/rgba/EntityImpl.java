package a.entity.gus06.convert.colortointarray.rgba;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250311";}
	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();
		
		return new int[]{r,g,b,a};
	}
}