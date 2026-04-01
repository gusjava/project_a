package a.entity.gus06.awt.robot.mouse.perform.releaseleft;

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
		releaseleft();
	}
	
	
	private void releaseleft()
	{robot.mouseRelease(InputEvent.BUTTON3_MASK);}
}
