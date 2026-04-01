package a.entity.gus06.sys.colorpicker1.pick.pixel1;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180226";}

	
	private Robot robot;

	public EntityImpl() throws Exception
	{robot = new Robot();}
	
	
	public Object g() throws Exception
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		return robot.getPixelColor(p.x, p.y);
	}
}
