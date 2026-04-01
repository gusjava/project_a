package a.entity.gus06.awt.robot.mouse.perform.pressright;

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
	}
	
	
	private void pressright()
	{robot.mousePress(InputEvent.BUTTON1_MASK);}
}
