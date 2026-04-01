package a.entity.gus06.awt.robot.mouse.perform.clickright2;

import a.framework.*;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20180301";}



	private Robot robot;
	
	public EntityImpl() throws Exception
	{
		robot = new Robot();
	}
	
	public void e() throws Exception
	{
		pressright();
		releaseright();
		
		pressright();
		releaseright();
	}
	
	
	
	private void pressright()
	{robot.mousePress(InputEvent.BUTTON1_MASK);}
	
	private void releaseright()
	{robot.mouseRelease(InputEvent.BUTTON1_MASK);}
}
