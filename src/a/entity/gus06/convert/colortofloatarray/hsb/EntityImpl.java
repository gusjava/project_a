package a.entity.gus06.convert.colortofloatarray.hsb;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),null);
		return hsb;
	}
}
