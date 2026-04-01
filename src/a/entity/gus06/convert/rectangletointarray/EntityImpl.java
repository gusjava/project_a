package a.entity.gus06.convert.rectangletointarray;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231106";}

	
	public Object t(Object obj) throws Exception
	{
		Rectangle rect = (Rectangle) obj;
		return new int[]{rect.x, rect.y, rect.width, rect.height};
	}
}