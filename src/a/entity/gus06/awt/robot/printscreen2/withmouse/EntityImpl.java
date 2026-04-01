package a.entity.gus06.awt.robot.printscreen2.withmouse;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.MouseInfo;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20220715";}
	
	public static final int MOUSE_RADIUS = 10;
	public static final Color MOUSE_COLOR = Color.YELLOW;


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
		BufferedImage image = robot.createScreenCapture(screenRect);
		
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		Point p = MouseInfo.getPointerInfo().getLocation();
		
		int xm = p.x - screenRect.x;
		int ym = p.y - screenRect.y;
		
		g2.setColor(MOUSE_COLOR);
		g2.fillOval(xm-MOUSE_RADIUS, ym-MOUSE_RADIUS, 2*MOUSE_RADIUS, 2*MOUSE_RADIUS);
		g2.dispose();
		
		return image;
	}
	
	public Object t(Object obj) throws Exception
	{return robot.createScreenCapture((Rectangle) obj);}
}