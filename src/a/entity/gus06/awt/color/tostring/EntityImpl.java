package a.entity.gus06.awt.color.tostring;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}

	
	
	public Object t(Object obj) throws Exception
	{
		Color c = (Color) obj;
		if(c==null) return "";
		return "["+c.getRed()+","+c.getGreen()+","+c.getBlue()+"]";
	}
}
