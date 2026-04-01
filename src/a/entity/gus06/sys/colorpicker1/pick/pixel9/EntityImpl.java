package a.entity.gus06.sys.colorpicker1.pick.pixel9;

import java.awt.Color;
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
		
		double r = 0;
		double g = 0;
		double b = 0;
		
		for(int i=-1;i<=1;i++) for(int j=-1;j<=1;j++)
		{
			Color c = robot.getPixelColor(p.x+i, p.y+j);
			
			r += c.getRed();
			g += c.getGreen();
			b += c.getBlue();
		}
		
		int r_av = (int) (r/9);
		int g_av = (int) (g/9);
		int b_av = (int) (b/9);
		
		return new Color(r_av,g_av,b_av);
	}
}
