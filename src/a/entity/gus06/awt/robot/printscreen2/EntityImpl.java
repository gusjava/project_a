package a.entity.gus06.awt.robot.printscreen2;

import a.framework.*;
import java.awt.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20220714";}


	private Service findScreenRect;
	private Robot robot;
	
	public EntityImpl() throws Exception
	{
		findScreenRect = Outside.service(this,"gus06.awt.screen.rect2");
		robot = new Robot();
	}

	public Object g() throws Exception
	{
		Rectangle screenRect = (Rectangle) findScreenRect.g();
		if(screenRect==null) return null;
		return robot.createScreenCapture(screenRect);
	}
	
	public Object t(Object obj) throws Exception
	{return robot.createScreenCapture((Rectangle) obj);}
}