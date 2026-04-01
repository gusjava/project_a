package a.entity.gus06.awt.color.array9;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200124";}

	
	
	public Object g() throws Exception
	{
		return new Color[]{
			Color.BLUE,
			Color.RED,
			Color.GREEN,
			Color.ORANGE,
			Color.CYAN,
			Color.YELLOW,
			Color.MAGENTA,
			Color.GRAY,
			Color.PINK};
	}
}