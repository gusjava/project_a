package a.entity.gus06.mouse.position;

import a.framework.*;
import java.awt.Point;
import java.awt.MouseInfo;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160407";}

	
	public Object g() throws Exception
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		return new int[]{p.x,p.y};
	}
}
