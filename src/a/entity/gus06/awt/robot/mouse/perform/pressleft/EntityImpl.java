package a.entity.gus06.awt.robot.mouse.perform.pressleft;

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
		pressleft();
	}
	
	
	private void pressleft()
	{robot.mousePress(InputEvent.BUTTON3_MASK);}
}
