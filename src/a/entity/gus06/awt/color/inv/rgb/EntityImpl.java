package a.entity.gus06.awt.color.inv.rgb;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}
	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		return new Color(255-r,255-g,255-b);
	}
}
