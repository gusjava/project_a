package a.entity.gus06.mouse.position.check.pos00;

import a.framework.*;
import java.awt.Point;
import java.awt.MouseInfo;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160511";}

	
	public boolean f(Object obj) throws Exception
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		return p.x==0 && p.y==0;
	}
}
