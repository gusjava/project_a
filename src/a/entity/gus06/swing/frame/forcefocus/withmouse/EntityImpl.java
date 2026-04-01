package a.entity.gus06.swing.frame.forcefocus.withmouse;

import a.framework.*;
import javax.swing.JFrame;
import java.awt.Point;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}


	private Service forceClickAt;

	public EntityImpl() throws Exception
	{
		forceClickAt = Outside.service(this,"gus06.mouse.forceclickat");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JFrame frame = (JFrame) obj;
		if(!frame.isVisible()) return;
		
		Point p = frame.getLocationOnScreen();
		int[] p1 = new int[]{p.x+5,p.y+5};
		
		forceClickAt.p(p1);
	}
}
