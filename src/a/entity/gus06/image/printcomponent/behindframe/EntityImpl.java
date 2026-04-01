package a.entity.gus06.image.printcomponent.behindframe;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import com.sun.jna.platform.WindowUtils;
import a.framework.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}


	private Robot robot;
	
	public EntityImpl() throws AWTException
	{robot = new Robot();}

	
	public Object t(Object obj) throws Exception
	{
		Component component = (Component)obj;
		Rectangle frame = component.getBounds();
		frame.setLocation(component.getLocationOnScreen());
		
		Window window = SwingUtilities.getWindowAncestor(component);
		
		System.setProperty("sun.java2d.noddraw","true");
		WindowUtils.setWindowAlpha(window,0f);
		
		//window.setVisible(false);
		
		BufferedImage image = robot.createScreenCapture(frame);
		
		System.setProperty("sun.java2d.noddraw","true");
		WindowUtils.setWindowAlpha(window,1f);
		
		//window.setVisible(true);
		
		return image;
	}
}
