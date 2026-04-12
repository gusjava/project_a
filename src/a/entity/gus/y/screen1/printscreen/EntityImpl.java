package a.entity.gus.y.screen1.printscreen;

import java.awt.Rectangle;
import java.awt.Robot;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, G, T {
	public String creationDate() {return "20240104";}

	private Service findScreenRect;
	private Rectangle screenRect;
	private Robot robot;

	public EntityImpl() throws Exception {
		findScreenRect = Outside.service(this, "gus.x.awt.screen.rect");
		screenRect = (Rectangle) findScreenRect.g();
		robot = new Robot();
	}

	public Object g() throws Exception {
		return robot.createScreenCapture(screenRect);
	}

	public Object t(Object obj) throws Exception {
		return robot.createScreenCapture((Rectangle) obj);
	}
}