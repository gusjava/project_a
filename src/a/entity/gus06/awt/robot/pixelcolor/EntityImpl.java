package a.entity.gus06.awt.robot.pixelcolor;

import a.framework.*;
import java.awt.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20161107";}


	private Robot robot;

	public EntityImpl() throws Exception
	{
		robot = new Robot();
	}

	public Object g() throws Exception
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		return robot.getPixelColor(p.x, p.y);
	}
	
	public Object t(Object obj) throws Exception
	{
		Point p = (Point) obj;
		return robot.getPixelColor(p.x, p.y);
	}
}
