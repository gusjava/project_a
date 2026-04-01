package a.entity.gus06.awt.color.tostring.rgba;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		if(c==null) return null;
		
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();
		
		return r+"-"+g+"-"+b+"-"+a;
	}
}