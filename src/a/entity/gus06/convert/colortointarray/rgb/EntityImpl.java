package a.entity.gus06.convert.colortointarray.rgb;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}
	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		return new int[]{r,g,b};
	}
}
